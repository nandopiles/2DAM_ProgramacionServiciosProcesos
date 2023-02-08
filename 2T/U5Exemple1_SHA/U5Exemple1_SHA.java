package u5Exemple1_SHA;

import java.security.MessageDigest;
import java.security.Provider;

public class U5Exemple1_SHA {
	public static void main(String[] args) throws Exception {
		String text = "Sóc el contingut d'un text";
		System.out.println("Text origen per a hash " + text);

		// CREE OBJECTE MessageDigest
		MessageDigest md = MessageDigest.getInstance("SHA");

		// ES POT CREAR EL RESUM AMB CLAU UTILITZANT digest(bytes[])
		String clave = "ClauXifratge";
		byte dataBytes[] = text.getBytes();
		md.update(dataBytes);
		byte resum_amb_clau[] = md.digest(clave.getBytes());

		// S'INTRODUEIX EL TEXT EN BYTES A RESUMIR
		md.update(text.getBytes());

		// ES CALCULA EL RESUM
		byte resum[] = md.digest();

		System.out.println("Nonmbre de bytes " + md.getDigestLength());
		System.out.println("Algorisme " + md.getAlgorithm());
		System.out.println("Missatge resum " + resum_amb_clau + new String(resum));

		// CONVERTEISC L'ARRAY DE BYTES A HEXADECIMAL
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < resum.length; i++) {
			String hex = Integer.toHexString(0xff & resum[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}

		System.out.println("Missatge en hexadecimal : " + hexString.toString());
		Provider proveedor = md.getProvider();
		System.out.println("Proveidor " + proveedor.toString());
	}
}