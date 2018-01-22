package karsiwek.day10

val input: String = "18,1,0,161,255,137,254,252,14,95,165,33,181,168,2,188";
val stdSuffix = arrayListOf<Byte>(17, 31, 73, 47, 23);
var listSize = 256;


tailrec fun reverse(list: List<Int>, from: Int, sizes: List<Int>, skipSize: Int=1): List<Int> {
    if(sizes.size==0) return list;
    var result = list.toMutableList();
    val size = sizes[0]-1;
    for(i in 0..((size)/2)){
        val tmp = result[(from+i)%result.size];
        result[(from+i)%result.size] = result[(from+size-i)%result.size];
        result[(from+size-i)%result.size] = tmp;
    }
    return reverse(result, (from+size+skipSize)%list.size, sizes.subList(1, sizes.size), skipSize+1);
}

fun main(args: Array<String>) {
    val data = input.map { el -> el.toByte() }.toMutableList();
    data.addAll(stdSuffix);
    var sixtyFourTimesData = mutableListOf<Byte>()
    for(i in 1..64){
        sixtyFourTimesData.addAll(data);
    }
    var result = reverse((0..(listSize - 1)).toList(), 0, sixtyFourTimesData.map { elem -> elem.toInt() })
            .chunked(16)
            .map { list -> list.reduceRight({ elem, acc -> acc.xor(elem) }) }
            .map { elem -> (if (elem<16) "0" else "") + Integer.toHexString(elem) }
            .reduce({l, r -> l+r});
    println(result);
}
