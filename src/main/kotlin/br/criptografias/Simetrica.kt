package main.br.criptografias

open class Simetrica{
	val z: CharArray = charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

	fun crypt(dado : String , chave : String): String {
		var arrayDado = mutableListOf<Int>()
		var arrayChave = mutableListOf<Int>()
		var arrayCriptograf = mutableListOf<Int>()
		var arrayFinalCripto = mutableListOf<Char>()
		//var dado = "Thiago".toLowerCase().toCharArray()
		//var chave = "Stuart".toLowerCase().toCharArray()

		for (i in dado.indices){
			arrayDado[i] = numberOf(dado[i])
			arrayChave[i] = numberOf(chave[i])
		}

		for(i in dado.indices){
			arrayCriptograf.add((arrayDado[i] + arrayChave[i]) % 36)
		}

		for(i in arrayCriptograf.indices){
			arrayFinalCripto[i] = characterOf(arrayCriptograf[i])
		}

		return arrayFinalCripto.toString()
	}



	fun decrypt(dadoCrypt : String , chave : String): String {
		var arrayDecrypt = mutableListOf<Int>()
		var arrayFinalDescripto = mutableListOf<Char>()
		var arrayDado = mutableListOf<Int>()
		var arrayChave = mutableListOf<Int>()
		//var dadoCrypt = "Thiago".toLowerCase().toCharArray()
		//var chave = "Stuart".toLowerCase().toCharArray()



		for (i in dadoCrypt.indices){
			arrayDado[i] = numberOf(dadoCrypt[i])
			arrayChave[i] = numberOf(chave[i])
		}

		for(i in dadoCrypt.indices){
			arrayDecrypt.add((arrayDado[i] - arrayChave[i]) % 36)
		}

		for(i in arrayDecrypt.indices){
			arrayFinalDescripto[i] = characterOf(arrayDecrypt[i])
		}

		return arrayFinalDescripto.toString()
	}

	fun numberOf(x: Char): Int {
		val number: Int
		var i: Int
		var found: Int = -1

		for(i in z.indices){
			if(x == z[i]){
				found = i
			}
		}

		return found
	}

	fun characterOf(y: Int): Char {
		return z[y]
	}

}