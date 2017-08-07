package exercicio;

import java.util.Arrays;

public class Quest�es {
	/*
	 * 1 - Que estrat�gia usada em algum algoritmo de ordena��o por compara��o voc�
	 * utilizaria para encontrar o menor elemento de um array? Implemente esse
	 * algoritmo. public int selecionaMenor(int[] array)
	 */
	public static int selecionaMenor(int[] array) {
		int menor = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] < menor) {
				menor = array[i];
			}
		}
		return menor;
	}

	/*
	 *  2 - Baseado na implementa��o anterior, implemente um outro algoritmo que
	 * seleciona o menor elemento de um array que seja maior que um outro
	 * elemento existente no array. public int selecionaMenor(int[] array, int
	 * existente)
	 */
	
	public static int selecionaMenor(int[] array, int existente) {
		int menor = selecionaMenor(array);
		boolean found = false;
		int j = array.length - 1;

		for (int i = 0; i < array.length; i++) {
			if (array[i] > menor && array[i] > existente) {
				menor = array[i];
				break;
			} else if (array[i] > menor && array[i] >= array[j]) {
				menor = array[i];
			}
			j--;
		}

		return menor;
	}
	
	
	/*3 -A k-esima estat�stica de ordem de um conjunto de dados � o k-esimo menor elemento desse
	conjunto de dados. Implemente um algoritmo que calcula a k-esima estat�stica de ordem
	de um array (assuma que k est� dentro do tamanho do array). Procure usar suas ideias
	implementadas em 1 e 2.
	public int orderStatistics(int[] array, int k)*/
	
	
	/* 1 - Forma "f�cil", ordernando o vetor, usando selection*/
	public static int easyOrderStatistics(int[] array, int k){
		ordernarVetor(array);
		return array[k];
	}
	/* 2- forma dificil, nao sei como funciona */
	public static int orderStatistics(int[] array, int k){
		k = k - 1; // since array index starts with 0
		return kSmall(array, 0, array.length - 1, k);
	}
	
	/*Peguei da net, n sei como funciona
	 * preguica de tentar btw
	 * */
	
	public static int kSmall(int[] arrA, int start, int end, int k) {
		int left = start;
		int right = end;
		int pivot = start;
		
		while (left <= right) {
			while (left <= right && arrA[left] <= arrA[pivot])
				left++;
			while (left <= right && arrA[right] >= arrA[pivot])
				right--;
			if (left < right) {
				swap(arrA, left, right);
				left++;
				right--;
			}
		}
		swap(arrA, pivot, right);
		if (pivot == k)
			return arrA[pivot];
		else if (pivot < k)
		
			return kSmall(arrA, pivot + 1, end, k);
		else

			return kSmall(arrA, start, pivot - 1, k);
	}

	private static void ordernarVetor(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int menor = i;
			for (int j = i + 1; j < array.length; j++) {
				if(array[j] < menor) menor = j;
			}
			swap(array, i, menor);
		}
	}
	/*
	 *  4 - Implemente um algoritmo recursivo para a exponencia��o, onde pow(a,b) onde a � a base
	e b � o expoente. Calcule o tempo de execu��o de seu algoritmo (voc� deve
	obrigatoriamente encontrar uma rela��o de recorr�ncia porque seu algoritmo � recursivo).
	T(n) = T( n - 1) + n
	* Custo = O(n)*/
	
	public int pow(int a, int b){
		if(b == 0){
			return 1;
		}
		return a * pow(a, b - 1);
	}
	
	/*
	 * 5 - Dado um array ordenado de numeros encontrar o par de n�meros no array
	 * cuja soma � o mais pr�ximo poss�vel de um n�mero dado x. Dica: o par de
	 * n�meros mais pr�ximo pode ser encontrado varrendo-se o array da esquerda
	 * para a direita e da direita para a esquerda, pegando n�meros cuja soma
	 * seja a m�nima possivel e se aproxime de determinado valor. Com isso,ao
	 * final da execu��o, voc� deve guardar os valores dos �ndices desses
	 * n�meros. Essa estrat�gia � explicada a seguir: a. Inicialize uma vari�vel
	 * �diff� com o m�ximo valor poss�vel (infinito) b. Inicialize duas
	 * vari�veis de �ndices left e right sendo 0 e array.length-1
	 * respectivamente c. Itere em loop enquanto left < right i. Se
	 * abs(array[right]-array[left] � x < diff) entao atualiza diff e os
	 * resultados (indices) de left e right ii. Sen�o se
	 * abs(array[right]-array[left] � x > diff) entao left++ iii. Senao right--
	 * *
	 */

	public static void parSoma(int[] array, int x){
		int start = 0;
		int end = array.length - 1;
		int aux = Integer.MAX_VALUE;
		int indexA = 0;
		int indexB = 0;
		
		while(start <= end){
			
			if(Math.abs(array[start] + array[end] - x) < aux){
				
				indexA = array[start];
				indexB = array[end];
				
				aux = Math.abs(array[start] + array[end] - x);
				
			}
			else if(Math.abs(array[end] - array[start] - x )> aux){
				start++;
			}
			else{
				end--;
			}
		}
		System.out.println("Os pares s�o " + indexA + ", " + indexB);
		
	}
	
	
	/*
	 *  6 - Imagine que voc� deseja ordenar um array de forma tal que, cada elemento indexado com
	par do array � maior que o elemento � sua esquerda e o elemento � sua direita. Sua
	resposta deve ser em um outro array. Seu algoritmo deve ser O(n.log n). Dica: procure
	enxergar a solu��o com um array ordenado. Exemplo:
	Input: {1, 2, 3, 4, 5, 6, 7}
	Output: {1, 7, 2, 6, 3, 5, 4}
	 */
	
	public static int[] orderna(int[] array){
		int[] auxArray = new int[array.length];
		/*
		 * Variaveis p/ pecorrer o  array
		 * do inicio ao final
		 * o aux � pra garantir que cada intera��o seja incrementado
		 * ps: to com preguica de testar sem ele btw*/
		int start = 0;
		int end = array.length - 1 ;
		int aux = 0;
		while(start <= end){
			if(aux % 2 == 0){
				auxArray[aux] = array[start];
				start++;
			}
			else{
				auxArray[aux] = array[end ];
				end--;
			}
			aux++;
		}
		/*M�todo p/ printar o array pra testes
		 * desconsidere */
		
		printArray(auxArray);
		return auxArray;
		
		
	}
	


	private static void printArray(int[] auxArray) {
		for (int i = 0; i < auxArray.length; i++) {
			System.out.println(auxArray[i] + " ");
		}
		
	}

	private static int selecionaMaior(int[] array) {
		int maior = array[0];
		for (int i = array.length - 1; i > 0; i--) {
			if(array[i] > maior){
				maior = array[i];
			}
		}
		return maior;
	}

	private static void swap(int[] array, int i, int j){
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/*Aqui s�o os testes que n�o sei fazer
	 * paguem p2 bem obg
	 * */
	
	
	public static void main(String[] args) {
		int[] array = new int[] {1, 2,3, 5, 7, 9, 11}; //ordenado fica : 1,3,4,5,8,67
		
		parSoma(array, 11);
	}
}

