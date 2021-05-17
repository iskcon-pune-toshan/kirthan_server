/*
 * package org.iskon.utils;
 * 
 * import java.security.NoSuchAlgorithmException; import
 * java.security.SecureRandom; import java.text.DecimalFormat; import
 * java.util.Random;
 * 
 * public class OTPGenerator { protected OTPGenerator() { } public static String
 * random(int size) {
 * 
 * StringBuilder generatedToken = new StringBuilder(); try { SecureRandom number
 * = SecureRandom.getInstance("SHA1PRNG"); for (int i = 0; i < size; i++) {
 * generatedToken.append(number.nextInt(9)); } } catch (NoSuchAlgorithmException
 * e) { e.printStackTrace(); } return generatedToken.toString(); }
 * 
 * public static String simple() { return new DecimalFormat("000000").format(new
 * Random().nextInt(999999)); } }
 */