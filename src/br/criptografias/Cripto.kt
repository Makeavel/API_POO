package br.generico.banco.criptografia

class Cripto {

    val z = {'a'; 'b'; 'c'; 'd'; 'e'; 'f'; 'g'; 'h'; 'i'; 'j'; 'k'; 'l'; 'm'; 'n'; 'o'; 'p'; 'q'; 'r';
        's'; 't'; 'u'; 'v'; 'w'; 'x'; 'y'; 'z'; ' '; '0'; '1'; '2'; '3'; '4'; '5'; '6'; '7'; '8'; '9';}

    fun criptografar(String: dado, String: chave): String{
        var arrayDado: Array<Integer>
        var arrayChave: Array<Integer>
        var arrayCriptograf: Array<Integer>
        var arrayFinalCripto: Array<String>

        var i = 0
        for(dado in arrayOf(dado)){
            arrayDado[i] = numberOf(dado[i])
            arrayChave[i] = numberOf(chave[i])
            i++
        }

        arrayCriptograf = arrayDado * arrayChave

        i = 0;
        for(i in arrayCriptograf.indices){
            arrayFinalCripto[i] = characterOf(arrayCriptograf[i])
        }

        return arrayFinalCripto
    }

    fun numberOf(Character: x): Integer{
        val number: Integer
        var i: Integer

        for(i in z.indices){
            if(x == z[i]){
                retorno = i
            }
        }

        return number
    }
}

    fun characterOf(Integer: y): Character{
        return z[y]
    }