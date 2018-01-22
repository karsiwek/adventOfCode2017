package karsiwek.day6


val input: String = """4	1	15	12	0	9	9	5	5	8	7	3	14	5	12	3"""

fun getSteps(input: String): Int {
    var state = input
            .split("\t"
            ).map { Integer.parseInt(it) }.toIntArray();

    var states = hashMapOf<String, Int>();
    var steps = 0;
    var out = false;
    while(!out) {
        if(states.containsKey(state.joinToString())){
            return steps - states.get(state.joinToString())!!
        }
        states.put(state.joinToString(), steps);

        var maxVal: Int = state.max()!!;
        val maxIndex = state.indexOf(maxVal);

        state[maxIndex] = 0;
        for(i in 1..maxVal){
            state[(maxIndex+i)%state.size]++;
        }
        steps++;
    }

    return steps;
}
fun main(args: Array<String>) {
    System.out.print(getSteps(input));
}
