package karsiwek.day13;

val input: String = """0: 3
1: 2
2: 4
4: 4
6: 5
8: 6
10: 8
12: 8
14: 6
16: 6
18: 9
20: 8
22: 6
24: 10
26: 12
28: 8
30: 8
32: 14
34: 12
36: 8
38: 12
40: 12
42: 12
44: 12
46: 12
48: 14
50: 12
52: 12
54: 10
56: 14
58: 12
60: 14
62: 14
64: 14
66: 14
68: 14
70: 14
72: 14
74: 20
78: 14
80: 14
90: 17
96: 18""";




fun getData(input:String) : Map<Int,Int> {
    return input.split("\n").map { line -> line.split(":").map { Integer.valueOf(it.trim()) } }.map{it[0] to it[1]}.toMap();
}

fun amICought(step: Int, layers: Int) : Boolean {
    if(layers==0 || step==0) return false;
    return step%((layers*2)-2)==0;
}


fun main(args: Array<String>) {
    val data = getData(input);

    var delay=0;
    var caught = true;
    while(caught){
        caught = false;
        var severity: Int = 0;

        for (iter in 0..data.keys.max()!!+1){
            if(amICought(iter+delay, data.get(iter)?:0)){
                severity += iter*data[iter]!!;
                caught = true;
            }
        }

        delay++;
    }
    println(delay-1);

}