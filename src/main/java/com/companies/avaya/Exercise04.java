package com.companies.avaya;

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.time.*;
import java.time.format.*;
import java.util.*;

class Solution04 {

    static DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");

    public int solution(String S) {
        List<Call> calls = new ArrayList<>();
        for (String line : S.split("\n")) {
            Call call = parseCall(line);
            calls.add(call);
        }
        int amount = 0;
        Long discountPhoneNum = findLongestCall(calls);
        for (Call call : calls) {
            if (!call.getPhoneNum().equals(discountPhoneNum)) {
                amount += calculateCallCost(call.getDuration());
            }
        }
        return amount;
    }

    // Return cost in cents
    public int calculateCallCost(LocalTime duration) {
        int hours = duration.getHour();
        int minutes = duration.getMinute();
        int seconds = duration.getSecond();

        if ((hours == 0) && (minutes < 5)) {
            int totalInSeconds = minutes * 60 + seconds;
            return totalInSeconds * 3;
        } else {
            int totalInMinutes = hours * 60 + minutes;
            if (seconds > 0) totalInMinutes++;
            return totalInMinutes * 150;
        }
    }

    // Return phone number that will get discount
    public Long findLongestCall(List<Call> calls){
        Long phoneNumber = 0L;
        LocalTime highestDuration = LocalTime.parse("00:00:00", format);

        for (Call call : calls){
            Long currentPN = call.getPhoneNum();
            LocalTime currentDurantion = call.getDuration();

            if (currentDurantion.isAfter(highestDuration)) {
                highestDuration = currentDurantion;
                phoneNumber = currentPN;
            } else if (currentDurantion == highestDuration) {
                if (currentPN < phoneNumber) {
                    phoneNumber = currentPN;
                }
            }
        }
        return phoneNumber;
    }

    public Call parseCall(String line) {
        String[] csv = line.split(",");
        LocalTime duration = LocalTime.parse(csv[0], format);
        String phoneStr = csv[1].replaceAll("-", "");
        return new Call(Long.valueOf(phoneStr), duration);
    }

    class Call {
        final Long phoneNum;
        final LocalTime duration;

        Call(Long phoneNum, LocalTime duration) {
            this.phoneNum = phoneNum;
            this.duration = duration;
        }

        public Long getPhoneNum() {
            return phoneNum;
        }

        public LocalTime getDuration() {
            return duration;
        }
    }
}