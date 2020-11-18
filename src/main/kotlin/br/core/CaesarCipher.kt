package main.br.criptografias

open class CaesarCipher{
	val CIPHER_BASE: CharArray = charArrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9')

	fun crypt(data: String , chave: String): String {
		var arrayDado = mutableListOf<Int>()
		var arrayChave = mutableListOf<Int>()
		var arrayCriptograf = mutableListOf<Int>()
		var arrayFinalCripto = mutableListOf<Char>()
		//var dado = "Thiago".toLowerCase().toCharArray()
		//var chave = "Stuart".toLowerCase().toCharArray()

		for (i in data.indices){
			arrayDado[i] = numberOf(data[i])
			arrayChave[i] = numberOf(chave[i])
		}

		for(i in data.indices){
			arrayCriptograf.add((arrayDado[i] + arrayChave[i]) % 36)
		}

		for(i in arrayCriptograf.indices){
			arrayFinalCripto[i] = characterOf(arrayCriptograf[i])
		}

		return arrayFinalCripto.toString()
	}



	fun decrypt(dataCrypt: String, chave: String): String {
		var arrayDecrypt = mutableListOf<Int>()
		var arrayFinalDescripto = mutableListOf<Char>()
		var arrayDado = mutableListOf<Int>()
		var arrayChave = mutableListOf<Int>()
		//var dadoCrypt = "Thiago".toLowerCase().toCharArray()
		//var chave = "Stuart".toLowerCase().toCharArray()



		for (i in dataCrypt.indices){
			arrayDado[i] = numberOf(dataCrypt[i])
			arrayChave[i] = numberOf(chave[i])
		}

		for(i in dataCrypt.indices){
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

		for(i in CIPHER_BASE.indices){
			if(x == CIPHER_BASE[i]){
				found = i
			}
		}

		return found
	}

	fun characterOf(y: Int): Char {
		return CIPHER_BASE[y]
	}

}