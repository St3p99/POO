package poo.util;

import java.util.Locale;

public class IO {
	private static java.util.Scanner keyboard = new java.util.Scanner(System.in);

	public static String readString(String prompt)
	{	print(prompt);
		String v = keyboard.nextLine();
		return v;
	}

	public static int readInt(String prompt)
	{	print(prompt);
		int v = keyboard.nextInt();
		keyboard.nextLine();
		return v;
	}

	public static long readLong(String prompt)
	{	print(prompt);
		long v = keyboard.nextLong();
		keyboard.nextLine();
		return v;
	}

	public static short readShort(String prompt)
	{	print(prompt);
		short v = keyboard.nextShort();
		keyboard.nextLine();
		return v;
	}

	public static byte readByte(String prompt)
	{	print(prompt);
		byte v = keyboard.nextByte();
		keyboard.nextLine();
		return v;
	}

	public static float readFloat(String prompt)
	{	print(prompt);
		float v = keyboard.nextFloat();
		return v;
	}

	public static double readDouble(String prompt)
	{	print(prompt);
		double v = keyboard.nextDouble();
		keyboard.nextLine();
		return v;
	}

	public static void println()
	{	System.out.println();
	}

	public static void print(Object s)
	{	System.out.print(s);
	}

	public static void println(Object s)
	{	System.out.println(s);
	}

	public static void print(int s)
	{	System.out.print(s);
	}

	public static void println(int s)
	{	System.out.println(s);
	}

	public static void print(long s)
	{	System.out.print(s);
	}

	public static void println(long s)
	{	System.out.println(s);
	}

	public static void print(short s)
	{	System.out.print(s);
	}

	public static void println(short s)
	{	System.out.println(s);
	}

	public static void print(byte s)
	{	System.out.print(s);
	}

	public static void println(byte s)
	{	System.out.println(s);
	}

	public static void print(float s)
	{	System.out.print(s);
	}

	public static void println(float s)
	{	System.out.println(s);
	}

	public static void print(double s)
	{	System.out.print(s);
	}

	public static void println(double s)
	{	System.out.println(s);
	}

	public static void print(char s)
	{	System.out.print(s);
	}

	public static void println(char s)
	{	System.out.println(s);
	}

	public static void print(boolean s)
	{	System.out.print(s);
	}

	public static void println(boolean s)
	{	System.out.println(s);
	}

	//MATRICI
	public static void printlnMatrix(int[][] m){
		for (int[] ints : m) {
			printlnArray(ints);
		}
	}


	public static void printlnMatrix(boolean[][] m){
		for (boolean[] booleans : m) {
			printlnArray(booleans);
		}
	}


	public static void printlnMatrix(float[][] m){
		for (float[] floats : m) {
			printlnArray(floats);
		}
	}


	public static void printlnMatrix(double[][] m){
		for (double[] doubles : m) {
			printlnArray(doubles);
		}
	}


	public static void printlnMatrix(long[][] m){
		for (long[] longs : m) {
			printlnArray(longs);
		}
	}


	public static void printlnMatrix(char[][] m){
		for (char[] chars : m) {
			printlnArray(chars);
		}
	}


	public static void printlnMatrix(String[][] m){
		for (String[] strings : m) {
			printlnArray(strings);
		}
	}


	public static void printlnMatrix(short[][] m){
		for (short[] shorts : m) {
			printlnArray(shorts);
		}
	}

	public static void printlnMatrix(Object[][] m){
		for (Object[] obj : m) {
			printlnArray(obj);
		}
	}


	public static void printlnArray(int[] v){
		int n = v.length;
		StringBuilder s = new StringBuilder(n*n);
		s.append("[");
		for (int i=0; i<n; i++)
			s.append(String.format("%3d",v[i])+", ");
		s.delete(s.length()-2, s.length());
		s.append("]");
		println(s);
	}




		public static void printlnArray(boolean[] v){
		int n = v.length;
		StringBuilder s = new StringBuilder(n*n);
		s.append("[");
		for (int i=0; i<n; i++)
			s.append(v[i]+", ");
		s.delete(s.length()-2, s.length());
		s.append("]");
		println(s);
	}


	public static void printlnArray(float[] v){
		int n = v.length;
		StringBuilder s = new StringBuilder(n*n);
		s.append("[");
		for (int i=0; i<n; i++)
			s.append(String.format(Locale.ROOT, "%3d",v[i])+", ");
		s.delete(s.length()-2, s.length());
		s.append("]");
		println(s);
	}


	public static void printlnArray(double[] v){
		int n = v.length;
		StringBuilder s = new StringBuilder(n*n);
		s.append("[");
		for (int i=0; i<n; i++)
			s.append(String.format(Locale.ROOT,"%7.3f",v[i])+", ");
		s.delete(s.length()-2, s.length());
		s.append("]");
		println(s);
	}

	public static void printlnArray(long[] v){
		int n = v.length;
		StringBuilder s = new StringBuilder(n*n);
		s.append("[");
		for (int i=0; i<n; i++)
			s.append(String.format(Locale.ROOT, "%5d",v[i])+", ");
		s.delete(s.length()-2, s.length());
		s.append("]");
		println(s);
	}


	
	public static void printlnArray(char[] v){
		int n = v.length;
		StringBuilder s = new StringBuilder(n*n);
		s.append("[");
		for (int i=0; i<n; i++)
			s.append(v[i]+", ");
		s.delete(s.length()-2, s.length());
		s.append("]");
		println(s);
	}


	public static void printlnArray(String[] v){
		int n = v.length;
		StringBuilder s = new StringBuilder(n*n);
		s.append("[");
		for (int i=0; i<n; i++)
			s.append(v[i]+", ");
		s.delete(s.length()-2, s.length());
		s.append("]");
		println(s);
	}


	public static void printlnArray(short[] v){
		int n = v.length;
		StringBuilder s = new StringBuilder(n*n);
		s.append("[");
		for (int i=0; i<n; i++)
			s.append(String.format(Locale.ROOT, "%2d",v[i])+", ");
		s.delete(s.length()-2, s.length());
		s.append("]");
		println(s);
	}

	public static void printlnArray(Object[] v){
		int n = v.length;
		StringBuilder s = new StringBuilder(n*n);
		s.append("[");
		for (int i=0; i<n; i++)
			s.append(v[i]+", ");
		s.delete(s.length()-2, s.length());
		s.append("]");
		println(s);
	}
}
