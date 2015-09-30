package br.com.eigmercados.runtime;

import br.com.eigmercados.unscramble.Unscrambler;


public class MainRuntime {
	public static void main(String[] argss) {
		String[] args = {"/home/pedro/Documents/transac.csv","902"};
		
		Unscrambler.getInstance().doUnscramble(args[0], args[1]);
	}
}
