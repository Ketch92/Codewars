import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;

public class CodewarsSolutions {

    public static void main(String[] args){
        System.out.println(maxBall(85));
    }
    
    static String alternateCase(final String string) {
        return java.util.Arrays.stream(string.split("")).map(i -> i.toLowerCase() == i ? i.toUpperCase() : i.toLowerCase()).
                collect(java.util.stream.Collectors.joining());
    }

    public static String [] solve(String [] arr){
        String[] streets = new String[arr.length];
        String[] directions = new String[arr.length];
        int i = 0;
        for(String str: arr){
            String[] s = str.split(" on ");
            directions[arr.length-1-i] = s[0];
            streets[arr.length-1-i] = s[1];
            i++;
        }
        java.util.ArrayList<String> list = new ArrayList<>();

        for(String str: directions){
            list.add(str);
        }

        list.remove("Begin");
        list.add(0, "Begin");
        directions = list.toArray(String[]::new);

        System.out.println(directions.length);
        directions = java.util.Arrays.stream(directions).map(x -> directionModifier(x)).toArray(String[]::new);

        for(int j = 0; j<arr.length; j++){
            arr[j] = directions[j]+" on "+streets[j];
        }
        return arr;
    }

    public static String directionModifier(String str){
        return str.contains("Right") ? str.replace("Right", "Left") :
                str.contains("Left") ? str.replace("Left", "Right") : str;
    }

    public static int makeMove(int sticks) {
        return sticks % 4;
    }

    public static String makeComplement(String dna) {
        String compDNA = "";
        for(char c:dna.toCharArray()){
            switch (c){
                case 'A': compDNA += "T"; break;
                case 'T': compDNA += "A"; break;
                case 'C': compDNA += "G"; break;
                case 'G': compDNA += "C"; break;
            }
        }
        return compDNA;
    }

    public static String reverseLetter(final String str) {
        return new StringBuilder(str.replaceAll("[^a-zA-Z]", "")).reverse().toString();
    }

    public static String highAndLow(String numbers) {
        int[] array = java.util.Arrays.stream(numbers.split(" ")).mapToInt(i -> Integer.parseInt(i)).toArray();
        return java.util.Arrays.stream(array).max().getAsInt()+" "+java.util.Arrays.stream(array).min().getAsInt();
    }

    public static int findNthOccurrence(String subStr, String str, int occurrence) {
        if(subStr.endsWith(subStr.substring(0, subStr.length()/2))){
            return findNthOccurrence(subStr.substring(0, subStr.length()/2), str, occurrence);
        }
        System.out.println(subStr);
        java.util.regex.Matcher matcher =
                java.util.regex.Pattern.compile(subStr).matcher("("+str+")+");
        java.util.regex.MatchResult[] mr = matcher.results().distinct().toArray(MatchResult[]::new);
//        System.out.println(mr.length);
        return mr.length==0 || occurrence>mr.length ? -1 : mr[occurrence-1].start()-1;
    }

    public static boolean validatePin(String pin) {
        if(pin.length() == 4 || pin.length() == 6){
            return pin.matches("\\d+");
        }
        return false;
    }

    public static int[] beggars(int[] values, int n) {
        int[] beggarsValues = new int[n];
        if(n==0){return new int[]{};}


        int beggarIndex = 0;
        for(int arrValue: values){
            if(beggarIndex == n){beggarIndex = 0;}
            beggarsValues[beggarIndex] += arrValue;
            beggarIndex++;
        }
        return beggarsValues;
    }

    public static int [] solve2(int [] arr){
        return reverseArray(Arrays.stream(reverseArray(arr)).distinct().toArray());
    }

    private static int[] reverseArray(int[] arr){
        int[] distinct = new int[arr.length];
        for(int i = 0; i<arr.length; i++){
            distinct[i] = arr[arr.length-1-i];
        } return distinct;
    }

    public static String solve3(String s){
        String str = new StringBuilder(s).reverse().toString().replaceAll(" ", "");
        int index = 0;

        while (s.contains(" ")){
            index = s.indexOf(" "); s = s.replaceFirst(" ", "_");
            str = new StringBuilder(str).insert(index, " ").toString();
        }

        return str;
    }

    public static String find(final String text) {
        int count = longestRepetitionOfChar(java.util.Arrays.stream(text.split("")).
                sorted().collect(java.util.stream.Collectors.joining()));

        String alfaBat = java.util.Arrays.stream("abcdefghijklmnopqrstuvwxyz".split("")).
                map(i -> i.repeat(count)).collect(java.util.stream.Collectors.joining());

        for(char c: text.toCharArray()){
            alfaBat = new StringBuilder(alfaBat).deleteCharAt(alfaBat.indexOf(c+"")).toString();
        }

        return alfaBat;
    }

    public static int longestRepetitionOfChar(String s){

        String distinct = java.util.Arrays.stream(s.split("")).
                distinct().collect(Collectors.joining());

        int countOfRepetition  = 0;
        for(char c : distinct.toCharArray()){
            String[] strArr = java.util.Arrays.stream(s.replaceAll("[^"+c+"]", " ").
                    trim().split(" ")).sorted().toArray(String[]::new);

            if (strArr[strArr.length-1].length() > countOfRepetition){
                countOfRepetition = strArr[strArr.length-1].length();
            }
        }
        return countOfRepetition;
    }

    public static String twoSort(String[] s) {
        java.util.Arrays.sort(s);
        StringBuilder res = new StringBuilder();
        for(int i = 0; i<s[0].length(); i++){
            res.append(s[0].charAt(i));
            if(i!=s[0].length()-1) res.append("***");
        }
        return res.toString();
    }

    public static int expressionsMatter(int a, int b, int c){
        return Math.max(Math.max(a+b+c, a*b*c), Math.max((a+b)*c, a*(b+c)));
    }

    public static int zeros(int n) {
        int count = 0;

        for (int i = 5; n / i >= 1; i *= 5)
            count += n / i;

        return count;
    }

    public static int[] sumOfN(int n) {
        int[] res = new int[Math.abs(n)+1];
        int sum = 0;
        for(int i = 0; i<res.length; i++){
            sum = i + sum;
            res[i] = sum;
        }
        return n>=0 ? res : java.util.Arrays.stream(res).map(x -> -x).toArray();
    }

    public static boolean isPrime(int num) {

        if (num <= 3) {return num > 1;}

        else if (num % 2 == 0 || num % 3 == 0){
            return false;
        }else{
            for (int i = 5; i * i <= num; i += 6){
                if (num % i == 0 || num % (i + 2) == 0){
                    return false;
                }
            }
            return true;
        }

    }

    public static int solve(int [] arr){
        int count = 0;
        for(int i = 0; i< arr.length-1; i +=2){

            count = arr[i] == (arr[i+1]+1) || (arr[i]+1) == (arr[i+1])  ? count+1 : count;
        }

        return count;
    }

    public static int solve(String s){
        String[] strArr = s.replaceAll("[^-?0-9]+", " ").trim().split(" ");
        int[] n = new int[strArr.length];

        for(int i=0; i<strArr.length; i++){
            n[i] = Integer.parseInt(strArr[i]);
        }
        java.util.Arrays.sort(n);

        return n[n.length-1];
    }

    public static boolean isEqually(String str) {
        int countN = 0;
        int countM = 0;

        char[] strCharArray = str.toCharArray();

        for(char c: strCharArray){
            if(c == 'N' || c == 'n') countN++;
            if(c == 'M' || c == 'm') countM++;
        }

        return countM==countN;
    }

    public static boolean hasNoneLetters(String blacklist, String phrase) {

        char[] blackListChar = blacklist.toLowerCase().toCharArray();

        for(char c: blackListChar){
            if(phrase.toLowerCase().contains(c+"")){return false;}
        }

        return true;
    }

    public static int[] findMultiples(int x, int n) {
        if(x<=0) return new int[]{0};
        int[] result = new int[x];

        for(int i = 0; i<result.length; i++){
            result[i] = n*(i+1);
        }

        return result;
    }

    public static String[] reverse(String[] a) {
        String str = "";

        for(String s: a){
            str = str+s;
        }

        str = new StringBuilder(str).reverse().toString();

        String[] res = new String[a.length];

        int beginIndex = 0;
        int endIndex = 0;

        for(int i =0; i<res.length; i++){
            endIndex = beginIndex + a[i].length();
            res[i] = str.substring(beginIndex, endIndex);
            beginIndex = endIndex;
        }

        return res;
//        Input:  {"Don't", "worry", ",", "be", "happy", "!"}
//        Output: {"!yppa", "heb,y", "r", "ro", "wt'no", "D"}
    }

    public double[] tribonacci(double[] s, int n) {

        if(n==0) return new double[]{};

        double[] triboRes = new double[n];

        for(int i = 0; i<=n; i++){
            if(i<=2){triboRes[i] = s[i]; continue;}
            triboRes[i] = triboRes[i-1] + triboRes[i-2] + triboRes[i-3];
        }

        return triboRes;
    }

    public static String[] wave(String str) {
        String[] s = java.util.Arrays.asList(new String[str.replaceAll(" ", "").length()]).stream().map(x -> str).toArray(String[]:: new );

        int indexOfArray = 0;
        int indexOfString = indexOfArray;
        while (indexOfArray<s.length){
            if(s[indexOfArray].charAt(indexOfString) == ' '){indexOfString++; continue;}
            s[indexOfArray] = new StringBuilder(s[indexOfArray]).replace(indexOfString, indexOfString+1, (s[indexOfArray].charAt(indexOfString)+"").toUpperCase()).toString();
            indexOfArray++;
            indexOfString++;
        }
        return s;
    }

    public static String formatDuration(int seconds) {
        if(seconds == 0){return "now";}

        final int SECONDS = getSeconds(seconds);
        final int MINUTES = getMinutes(seconds);
        final int HOURS = getHours(seconds);
        final int DAYS = getDays(seconds);
        final int YEARS = getYears(seconds);

        final int[] DIGITS = new int[]{YEARS, DAYS, HOURS, MINUTES, SECONDS};

        java.util.ArrayList<String> list = new java.util.ArrayList<>();

        for(int i = 0; i<DIGITS.length; i++){
            String s = appendDigit(DIGITS, i);
            if(!s.isEmpty()){list.add(s);}
        }
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<list.size(); i++){
            sb.append(list.get(i));
            if(list.size()>1){sb.append(", ");}
            if(list.size()>1 && i==list.size()-2){sb.append("and ");}
        }

        if(list.size()>=2){sb.deleteCharAt(sb.lastIndexOf(","));
            sb.deleteCharAt(sb.lastIndexOf(","));}

        return sb.toString().trim();
    }

    private static int getYears(int seconds){
        return seconds/(24*3600*365);
    }

    private static int getDays(int seconds){
        return (seconds-getYears(seconds)*((24*3600*365)))/(24*3600);
    }

    private static int getHours(int seconds){
        return (seconds-(getYears(seconds)*(24*3600*365)+getDays(seconds)*(24*3600)))/3600;
    }

    private static int getMinutes(int seconds){
        return (seconds-(getYears(seconds)*(24*3600*365)+getDays(seconds)*(24*3600)+getHours(seconds)*3600))/60;
    }

    private static int getSeconds(int seconds){
        return (seconds-(getYears(seconds)*(24*3600*365)+getDays(seconds)*(24*3600)+getHours(seconds)*3600+getMinutes(seconds)*60));
    }

    private static String appendDigit(int[] array, int index){
        StringBuilder strResult = new StringBuilder("");
        String sequenceToAppend = "";

        switch (index){
            case 0: sequenceToAppend = array[index]==1 ? " year" : " years"; break;
            case 1: sequenceToAppend = array[index]==1 ? " day" : " days"; break;
            case 2: sequenceToAppend = array[index]==1 ? " hour" : " hours"; break;
            case 3: sequenceToAppend = array[index]==1 ? " minute" : " minutes"; break;
            case 4: sequenceToAppend = array[index]==1 ? " second" : " seconds"; break;
        }

        if(array[index]!=0){
            strResult.append(array[index] + sequenceToAppend);
        }

        return strResult.toString();
    }

    public static String pigIt(String str) {
        String[] array = str.split(" ");
        str = "";
        for(String s: array){
            str = new StringBuilder(str).append(swapLetters(s)+"ay ").toString();
        }
        return str.trim();
    }

    private static String swapLetters(String word){
        if(!java.util.regex.Pattern.matches("[a-zA-Z]+", word)){ return word;}
        return new StringBuilder(word).append(word.charAt(0)).append("ay ").deleteCharAt(0).toString();
    }

    public static String maskify(String str) {
        return str.length()<=4 ? str : new StringBuilder(str).replace(0, str.length()-4, "#".repeat(str.length()-4)).toString();
    }

    public static String cleanString(String s) {
        while (s.startsWith("#")){
            s = new StringBuilder(s).deleteCharAt(0).toString();
        }

        if(s.isEmpty()){return "";}

        int countHastTags = 0;

        int index = s.length()-1;

        while (index>=0 && !s.isEmpty()){
            if(s.charAt(index)=='#'){
                countHastTags +=1;
                s = new StringBuilder(s).deleteCharAt(index).toString();
                index -= 1; continue;
            } else if(s.charAt(index)!='#' && countHastTags>0){
                s = new StringBuilder(s).deleteCharAt(index).toString();
                countHastTags -= 1;
                index -= 1; continue;
            }
            index -= 1;
        }

        return s;
    }

    public static int solveParity(int [] arr){
        java.util.Arrays.sort(arr);
        int index = 0;
        while (arr[index]<0){
            index++;
        }
        int[] negatives = java.util.Arrays.copyOf(arr, index);
        arr = java.util.Arrays.copyOfRange(arr, index, arr.length);

        if(negatives.length>arr.length){
            arr = java.util.Arrays.stream(arr).map(i -> -i).sorted().toArray(); }
        else {
            negatives = java.util.Arrays.stream(negatives).map(i -> -i).sorted().toArray();}

        return arr.length>negatives.length ? arr[Arrays.mismatch(arr, negatives)]: negatives[Arrays.mismatch(arr, negatives)];
    }

    public static int nthPower(int[] array, int n) {
        return n>=array.length ? -1 : (int)Math.pow(array[n],n);
    }

    public static int [] nameValue(String [] arr){
        java.util.ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i<arr.length; i++){list.add(stringToNumber(arr[i])*(i+1));
        }
        return list.stream().mapToInt(i->i).toArray();
    }

    private static int stringToNumber(String str){
        if(str.isBlank()){return 0;}

        int result = 0;

        for(char c: str.toCharArray()){
            result += c!=' ' ? (c-96): 0;
        }
        return result;
    }

    static String[] unusualLexOrder(String[] words) {
        return Arrays.stream(words).
                map(x -> new StringBuilder(x).reverse().toString()).
                sorted().map(x -> new StringBuilder(x).reverse().toString()).
                toArray(String[]::new);
    }

    public static String print(int n) {
        if(n<=0 || n == 2 || n % 2 == 0){return null;}
        if(n==1){return "*\n";}

        int countSpaces = n-1;
        int countAsterisks = 1;
        String result = "";

        while (countAsterisks<=n){
            result = new StringBuilder(result).
                    append(" ".repeat(countSpaces/2)).
                    append("*".repeat(countAsterisks)).
                    append("\n").toString();
            if(countAsterisks==n) break;
            countSpaces -=2;
            countAsterisks += 2;
        }
        while (countAsterisks>=3){
            countSpaces +=2;
            countAsterisks -= 2;
            result = new StringBuilder(result).
                    append(" ".repeat(countSpaces/2)).
                    append("*".repeat(countAsterisks)).
                    append("\n").toString();
        }

        return result;
    }

    public static String convertTime(int timeDiff) {
        final int DAY = 24*3600;
        final int HOUR = 3600;
        final int MINUTE = 60;

        int days = timeDiff/DAY;
        int hours = (timeDiff-days*DAY)/HOUR;
        int minutes = (timeDiff-days*DAY-hours*HOUR)/MINUTE;
        int seconds = (timeDiff-days*DAY-hours*HOUR-minutes*MINUTE);

        return new StringBuilder("").append(days+" ").
                append(hours+" ").append(minutes+" ").append(seconds).
                toString();
    }

    public static Object[] longestRepetition(String s) {

        if(s.isEmpty()){return new Object[]{"", 0};}

        java.util.ArrayList<int[]> list = new java.util.ArrayList<>();
        String str = java.util.Arrays.stream(s.split("")).distinct().collect(java.util.stream.Collectors.joining());

        for(char charFromStr: str.toCharArray()){
            //розділення на масив за допомогою space, а не паттерну
            String[] arr = s.replaceAll("[^"+charFromStr+"]", " ").split(" ");
            java.util.Arrays.sort(arr);
            list.add(new int[]{charFromStr, arr[arr.length-1].length()});
        }

        list.sort((o1, o2) -> o1[1]>o2[1] ? -1 : o1[1]==o2[1] ? 0 : 1);

        int maxRepet = list.get(0)[1];

        for(int[] i: list){
            if(i[1] == maxRepet){
                return new Object[]{java.lang.Character.toString(i[0]), i[1]};
            }
        }

        return new Object[]{"", 0};
    }

    public static Object[] longestRepetition2(String s) {
        if(s.isEmpty()){return new Object[]{"", 0};}

        String distinct = java.util.Arrays.stream(s.split("")).
                distinct().collect(Collectors.joining());

        char charToReturn = 'a';
        int countOfRepetition  = 0;
        for(char c : distinct.toCharArray()){
            String[] strArr = java.util.Arrays.stream(s.replaceAll("[^"+c+"]", " ").
                    trim().split(" ")).sorted().toArray(String[]::new);

            if (strArr[strArr.length-1].length() > countOfRepetition){
                countOfRepetition = strArr[strArr.length-1].length();
                charToReturn = c;
            }
        }

        return new Object[]{charToReturn+"", countOfRepetition};
    }

    public static String[] dup(String[] arr){
        return java.util.Arrays.stream(arr).map(x -> removeDuplicates(x)).toArray(String[]::new);
    }

    private static String removeDuplicates(String str){

        for (String x : str.split("")) {
            str = str.contains(x.concat(x)) ? str.replaceAll(x.concat(x), x) : str;
        }

        return str;
    }

    public static int digital_root(int n) {
        n = java.util.Arrays.stream(Integer.toString(n).
                split("")).
                mapToInt((i -> Integer.parseInt(i))).
                sum();
        return  Integer.toString(n).length() > 1 ? digital_root(n) : n;
    }

    public static int[] splitByValue(int k, int[] elements) {
        int[] higher = java.util.Arrays.stream(elements).filter(i -> i >=k).toArray();
        int[] lower = java.util.Arrays.stream(elements).filter(i -> i < k).toArray();
        int[] result = Arrays.copyOf(lower, lower.length+higher.length);
        System.arraycopy(higher, 0, result, lower.length, higher.length);
        return result;}

    public static int add(int num1,int num2){

        if(num1<num2){
            int i = num1;
            num1 = num2;
            num2 = i;
        }
        if((num1<10 && num2<10) || num1+num2<20) {return num1+num2;}
        else {
            return sillyAddition(num1, num2);
        }
    }

    private static int sillyAddition(int num1, int num2){
        String[] str1 = new StringBuilder(num1+"").reverse().toString().split("");
        String[] str2 =  new StringBuilder(num2+"").reverse().toString().split("");

        for(int i = 0; i<str2.length; i++){
            str1[i] = Integer.parseInt(str1[i])+Integer.parseInt(str2[i])+"";
        }
        java.util.Collections.reverse(java.util.Arrays.asList(str1));
        return Integer.parseInt(java.util.Arrays.stream(str1).collect(java.util.stream.Collectors.joining()));
    }

    public static String randomString(int length){
        String str = "";
        for (int i = 0; i < length; i++) {
            str = str + new StringBuilder("").append(randomChar().repeat(randomInt(10)));
        }
        return str.substring(0, length);
    }

    public static int randomInt(int upperLimit){
        return (int)Math.round(Math.random()*upperLimit);
    }

    public static int randomInt(int lowerLimit, int upperLimit){
        return (int)Math.round(lowerLimit+Math.random()*(upperLimit-lowerLimit));
    }

    public static String randomChar(){
        return java.lang.Character.toString(randomInt(32, 126));
    }

    public static List<String> number(List<String> lines) {
        java.util.List<String> result = new java.util.ArrayList<>();
        for (int i = 0; i<lines.size(); i++){
            result.add((i+1)+": "+lines.get(i));
        }
        return result;
    }

    public static String songDecoder (String song) {
        song = song.replaceAll("WUB", " ").replaceAll("  ", " ");
        return song.contains("  ") || song.contains("WUB") ? songDecoder(song).trim() : song.trim();
    }

    public static boolean isPalindrome(String string) {
        if (string.isEmpty() || string.length()==1) return true;
        return string.endsWith(string.charAt(0) + "") && isPalindrome(string.substring(1, string.length() - 1));
    }

    public static String findScreenHeight(int width, String ratio) {
        String[] aspectRatioArray = ratio.split(":");
        return width+"x"+Integer.parseInt(aspectRatioArray[1])*width/Integer.parseInt(aspectRatioArray[0]);
    }

    public static String multiTable(int num) {
        String result = "";
        for(int i = 1; i <=10; i++){
            result = new StringBuilder(result).append(i).append(" * ").
                    append(num). append(" = ").append(i*num+"\n").toString();
        }
        return new StringBuilder(result).deleteCharAt(result.length()-1).toString();
    }


    /*
    Codewars - Form the largest
    https://www.codewars.com/kata/5a4ea304b3bfa89a9900008e
     */
    public static long maxNumber(long n) {
        return Long.parseLong(java.util.Arrays.stream(Long.toString(n).split("")).
                sorted(java.util.Collections.reverseOrder()).collect(java.util.stream.Collectors.joining()));
    }

    /*
    In this kata you are given a string for example:
    "example(unwanted thing)example"
    Your task is to remove everything inside the parentheses as well as the parentheses themselves.
    The example above would return:
    "exampleexample"
    https://www.codewars.com/kata/5f7c38eb54307c002a2b8cc8/train/java
     */
    public static String removeParentheses(final String str) {

        String newStr = str.replaceFirst("\\([^()]*\\)", "");

        if (!newStr.contains("(")) {
            return newStr;
        }

        return removeParentheses(newStr);
    }

    public static boolean getXO (String str) {
        str = str.toLowerCase().replaceAll("[^ox]", "");
        return str.isEmpty() || str.replaceAll("x", "").
                length() == str.replaceAll("o", "").
                length();
    }

    public static String someString(String[] arrayString) {
        String result = java.util.Arrays.stream(arrayString).collect(Collectors.joining(" "));
        if(result.isEmpty()) {return "We have no words here!";}
        return  result.replaceFirst(result.charAt(0)+"",
                (result.charAt(0)+"").toUpperCase()).concat(".");
    }

    /*
    You throw a ball vertically upwards with an initial speed v (in km per hour).
    The height h of the ball at each time t is given by h = v*t - 0.5*g*t*t where
    g is Earth's gravity (g ~ 9.81 m/s**2). A device is recording at every tenth of second
    the height of the ball. For example with v = 15 km/h the device gets something of the
    ollowing form: (0, 0.0), (1, 0.367...), (2, 0.637...), (3, 0.808...), (4, 0.881..) ...
    where the first number is the time in tenth of second and the second number the height in meter.

    Task
    Write a function max_ball with parameter v (in km per hour) that returns
    the time in tenth of second of the maximum height recorded by the device.
    https://www.codewars.com/kata/566be96bb3174e155300001b/train/java
     */
    public static int maxBall(int v0) {
        double velocity = (double)v0*1000/3600;
        java.util.ArrayList<Double> list = new java.util.ArrayList<>();
        double time = 0;

        while(time<500){
            list.add(velocity*time - 0.5*9.81*time*time);
            time +=0.1;
        }

        return list.indexOf(java.util.Collections.max(list));
    }
}
