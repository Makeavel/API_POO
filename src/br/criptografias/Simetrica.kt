package br.criptografias

import kotlin.text.*

open class Simetrica{

	fun crypt(){
		var arrayDado = mutableListOf<Int>()
		var arrayChave = mutableListOf<Int>()
		var arrayCriptograf = mutableListOf<Int>()
		var arrayFinalCripto = mutableListOf<Char>()
		var dado = "Thiago".toLowerCase().toCharArray()
		var chave = "Stuart".toLowerCase().toCharArray()
		
		for (i in dado.indices){
			arrayDado[i] = numberOf(dado[i])
			arrayChave[i] = numberOf(chave[i])
		}
		
		arrayCriptograf = for(i in arrayDado.toArray().size) arrayDado * arrayChave
		
		for(i in arrayCriptograf.indices){
			arrayFinalCripto[i] = characterOf(arrayCriptograf[i])
		}
		
		var dadoFinalCripto = arrayFinalCripto.reduce { count, number -> count + number }
		
		return dadoFinalCripto
		
	}
	
	fun numberOf(x: Char): Int {
		val number: Int
		var i: Int
		val z = getConst
		
		for(i in z.size){
			if(x == z[i]){
				return i
			}
		}
	}
	
	fun characterOf(y: Int): Char {
		return z[y]
	}
	
}