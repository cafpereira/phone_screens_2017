package com.companies.apple;

// This is a description of a small system. The coding exercise is to model the components and processes in this system
// in an object oriented way and be able to run a simulation of the process.
//
// A widget Factory consists of three departments - Assembly, QualityControl and Shipping.
// The factory is managed by a Factory Manager.
// The Factory makes and ships three kinds of widgets: Indigo, Tan and Mauve.
// Indigo and Tan support function shake.
// Tan and Mauve support function drag.
// Both shake and drag functions return 0 if the widget is in working condition, and -1 if the widget is defective.
// Historically, a certain percentage of Indigo, Tan and Mauve widgets are defective.
//
// Assembly department makes the widgets in independent assembly lines for each widget.
// After each widget is made, Assembly sends it to QualityControl and sends a notification to Factory Manager.
//
// QualityControl department doesn't know about Indigo, Tan or Mauve.
// They only know about widgets that can be shaken or dragged.
// QualityControl tests every widget, and if a widget is not defective, it is sent to Shipping and QualityControl notifies Factory Manager.
//
// When a widget is received by Shipping department a notification of the type of widget received is sent to Factory Manager.
//
// Model the the factory (and the departments), the Factory Manager, widget types and functions, and the inter-department processes.
//
// Run a simulation of the factory for:
// 1. a configurable number of seconds (see test case)
// 2. a configurable production rate per second for each kind of widget, and
// 3. a configurable defect rate for each kind of widget
//
// At the end of the simulation, the test will verify that the number of widgets assembled (given the rate) = number of defective widgets + number of shipped widgets.
//
// */

//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.JUnitCore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Factory {

    final FactoryManager manager;
    final Assembly assembly;
    final QualityControl qc;
    final Shipping shipping;

    final FactoryConf conf;

    public Factory(int indigoProductionRate,  int tanProductionRate, int mauveProductionRate,
                   double indigoErrorRate, double tanErrorRate, double mauveErrorRate) {

        // Initialize factory configurations
        conf = new FactoryConf();
        conf.put(Indigo.class, new ProductionConf(indigoErrorRate, indigoProductionRate));
        conf.put(Tan.class, new ProductionConf(tanErrorRate, tanProductionRate));
        conf.put(Mauve.class, new ProductionConf(mauveErrorRate, mauveProductionRate));

        // Initialize departments and factory manager
        manager = new FactoryManager(conf);
        assembly = new Assembly(this);
        qc = new QualityControl(this);
        shipping = new Shipping(this);
    }

    public void run(int simulationDurationInSeconds){
        for (Class<? extends Widget> type : conf.allTypes){
            int producedCount = 0;
            while(++producedCount <= conf.get(type).prodRate * simulationDurationInSeconds) {
                assembly.produce(type);
            }
        }
    }

    // Factory delegate manager reports
    public int shipped(Class<? extends Widget> widgetType) {
        return manager.shippedReport.get(widgetType);
    }

    public int assembled(Class<? extends Widget> widgetType) {
        return manager.assembleReport.get(widgetType);
    }

    public int defective(Class<? extends Widget> widgetType) {
        return manager.assembleReport.get(widgetType) - manager.passReport.get(widgetType);
    }
}

class FactoryConf extends HashMap<Class<? extends Widget>, ProductionConf>{
    final List<Class<? extends Widget>> allTypes = Arrays.asList(Indigo.class, Tan.class, Mauve.class);
}

class ProductionConf {
    final double errRate;
    final int prodRate;

    ProductionConf(double errRate, int prodRate) {
        this.errRate = errRate;
        this.prodRate = prodRate;
    }
}

abstract class Department {
    final Factory factory;

    Department(Factory factory) {
        this.factory = factory;
    }
}

class Assembly extends Department {
    Assembly(Factory factory) {
        super(factory);
    }

    public void produce(Class<? extends Widget> widgetType) {
        boolean isDefective = Math.random() < factory.conf.get(widgetType).errRate;
        try {
            Widget w = widgetType.newInstance();
            w.setDefective(isDefective);
            factory.manager.deviceAssembled(widgetType);
            factory.qc.checkDevice(w);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class QualityControl extends Department {

    QualityControl(Factory factory) {
        super(factory);
    }

    public void checkDevice(Widget widget){
        int shakeRes = 0;
        if (widget instanceof Shakeable) {
            shakeRes = ((Shakeable) widget).shake();
        }

        int dragRes = 0;
        if (widget instanceof Draggable) {
            dragRes = ((Draggable) widget).drag();
        }

        // Device has no defects
        if (shakeRes == 0 && dragRes == 0){
            factory.shipping.shipDevice(widget);
            factory.manager.checkPassed(widget.getClass());
        }
    }
}

class Shipping extends Department {
    Shipping(Factory factory) {
        super(factory);
    }

    public void shipDevice(Widget widget){
        // ready to ship..
        factory.manager.deviceShipped(widget.getClass());
    }
}

enum Stage {
    ASSEMBLED,
    PASSED,
    SHIPPED;
}

class FactoryManager {

    final Map<Class<? extends Widget>, Integer> assembleReport = new HashMap<Class<? extends Widget>, Integer>();
    final Map<Class<? extends Widget>, Integer> shippedReport = new HashMap<Class<? extends Widget>, Integer>();
    final Map<Class<? extends Widget>, Integer> passReport = new HashMap<Class<? extends Widget>, Integer>();

    final FactoryConf factoryConf;

    FactoryManager(FactoryConf conf){
        // Initialize all reports
        for (Class<? extends Widget> type : conf.allTypes){
            assembleReport.put(type, 0);
            shippedReport.put(type, 0);
            passReport.put(type, 0);
        }
        this.factoryConf = conf;
    }

    public void increment(Class<? extends Widget> type, Stage stg){
        int inc;
        switch(stg){
            case ASSEMBLED:
                inc = assembleReport.get(type) + 1;
                assembleReport.put(type, inc);
                break;
            case SHIPPED:
                inc = shippedReport.get(type) + 1;
                shippedReport.put(type, inc);
                break;
            case PASSED:
                inc = passReport.get(type) + 1;
                passReport.put(type, inc);
                break;
        }
    }

    public void deviceAssembled(Class<? extends Widget> type){
        increment(type, Stage.ASSEMBLED);
    }
    public void deviceShipped(Class<? extends Widget> type){
        increment(type, Stage.SHIPPED);
    }
    public void checkPassed(Class<? extends Widget> type){
        increment(type, Stage.PASSED);
    }
}


interface Shakeable{
    public int shake();
}

interface Draggable {
    public int drag();
}

abstract class Widget {
    boolean defective;

    public void setDefective(boolean defective){
        this.defective = defective;
    }

}

class Indigo extends Widget implements Shakeable {
    public int shake(){
        return defective ? -1 : 0;
    }
}

class Tan extends Widget implements Shakeable, Draggable {
    public int shake(){
        return defective ? -1 : 0;
    }
    public int drag(){
        return defective ? -1 : 0;
    }
}

class Mauve extends Widget implements Draggable {
    public int drag(){
        return defective ? -1 : 0;
    }
}

class Solution {

//    @Test
//    public void simulation() throws Exception {
//        double indigoErrorRate = 0.02;
//        double tanErrorRate = 0.01;
//        double mauveErrorRate = 0.05;
//        int indigoProductionRate = 10;
//        int tanProductionRate = 5;
//        int mauveProductionRate = 1;
//        int simulationDurationInSeconds = 2;
//
//        final Factory factory = new Factory(indigoProductionRate, tanProductionRate, mauveProductionRate, indigoErrorRate, tanErrorRate, mauveErrorRate);
//        factory.run(simulationDurationInSeconds);
//
//        final List<Class<? extends Widget>> widgetTypes = getWidgetTypes();
//        Assert.assertTrue(!widgetTypes.isEmpty());
//        widgetTypes.stream().forEach(widgetType -> {
//            Assert.assertEquals(factory.shipped(widgetType), factory.assembled(widgetType) - factory.defective(widgetType));
//        });
//        Assert.assertEquals(new Integer((indigoProductionRate + tanProductionRate + mauveProductionRate) * simulationDurationInSeconds), widgetTypes.stream().map(c -> factory.assembled(c)).reduce(0, (x,y) -> x+y));
//    }

    public static void main(String[] args) throws Exception {
//        JUnitCore.main("Solution");
    }

    public List<Class<? extends Widget>> getWidgetTypes() {
        return Arrays.asList(Indigo.class, Tan.class, Mauve.class);
    }
}