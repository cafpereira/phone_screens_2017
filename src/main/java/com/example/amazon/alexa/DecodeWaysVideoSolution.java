package com.example.amazon.alexa;

/**
 Count number of encodings of a digit string.
 link: https://www.youtube.com/watch?v=aCKyFYF9_Bg&t=309s
 git: https://github.com/IDeserve/learn/blob/master/EncodeDecode.java

 */
public class DecodeWaysVideoSolution {

    public static void main(String[] args) {
        String input = "1123";
    System.out.println("Response for " + input + ": " + decode_recur(input));
    }

    public static int decode_recur(String message)
    {

        int msgLen = message.length();
        if(msgLen == 0 || msgLen == 1)
            return 1;

        int count = 0;

        if(message.charAt(msgLen - 1 ) > '0')//last digit
            count = decode_recur(message.substring(0, msgLen - 1));//trimmed message by decreasing length by 1


        if((message.charAt(msgLen - 2) < '2') || ( message.charAt(msgLen - 2) == '2' &&  message.charAt(msgLen - 1) < '7' ) )
            count += decode_recur(message.substring(0, msgLen - 2));

        return count;
    }

    public static int decode_dp(String message)
    {
        int msgLen = message.length();
        int[] decodeCount = new int[msgLen + 1];

        decodeCount[0] =1;
        decodeCount[1] =1;


        for(int i=2; i< msgLen + 1; i++)
        {
            if(message.charAt(i - 1 ) > '0')
                decodeCount[i] = decodeCount[i-1];

            if((message.charAt(i - 2) < '2') || ( message.charAt(i - 2) == '2' &&  message.charAt(i - 1) < '7' ) )
                decodeCount[i] = decodeCount[i] + decodeCount[i-2];
        }

        return decodeCount[msgLen];
    }
}


