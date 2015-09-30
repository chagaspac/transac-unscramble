package br.com.eigmercados.unscramble;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.com.eigmercados.common.Utils;

public class Unscrambler {

	private Unscrambler(){
	}
	private static Unscrambler INSTANCE = null;

	public static Unscrambler getInstance(){
		if(INSTANCE == null) INSTANCE = new Unscrambler();
		return INSTANCE;
	}

	public void doUnscramble(String filename, String code){
		try {
			StringBuilder sb = new StringBuilder();
			String[] lines = Utils.getInstance().readLines(filename);
			System.out.println("## I have the file");

			Map<String,String> map = getStructure(code);
			System.out.println("## I have the structure");

			System.out.println("## Lets Unscrable it!");

			for (Map.Entry<String, String> entry : map.entrySet()){
				sb.append(entry.getKey().split("@")[0] + ";");
			}
			sb.append("\n");
			for(String line : lines){
				for (Map.Entry<String, String> entry : map.entrySet()){
					String key = entry.getKey().split("@")[0];
					String type = entry.getKey().split("@")[1];
					int decimal = 0;
					if(entry.getKey().split("@").length == 3) 
						decimal = Integer.parseInt(entry.getKey().split("@")[2]);
					int startIndex = Integer.parseInt(entry.getValue().split("@")[0]);
					int endIndex = Integer.parseInt(entry.getValue().split("@")[1]);

					String value = formatValue(line.substring(startIndex-1, endIndex),type,decimal);
					sb.append(value + ";");
				}
				sb.append("\n");
			}
			Utils.getInstance().writeTextToFile(filename.substring(0, filename.indexOf(".")) + "_unscrambled.csv", sb.toString());
			System.out.println("## File writed!");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private String formatValue(String value, String type, int decimal) {
		String formatedValue = value;
		if(type != null){
			switch(type){
			case "String":
				break;
			case "Decimal":
				if(!"".equals(value.trim())){
					formatedValue = value.substring(0,value.length()-decimal) + "." + value.substring(value.length()-decimal,value.length());
				}
				break;
			case "Date":
				if(!"00000000".equals(value) && !"".equals(value.trim())){
					formatedValue = value.substring(0,2) + "/" + value.substring(2,4) + "/" + value.substring(4);
				}
				break;
			default:
				break;
			}
		}
		return formatedValue;
	}

	private Map<String,String> getStructure(String code){
		Map<String,String> map = null;
		switch(code){
		case "902":
			map = get902Placa();
			break;
		default:
			map = null;
			break;
		}
		return map;
	}

	private Map<String,String> get902Placa(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("parte-fixa-transação@String",1 + "@" + 37);
		map.put("código-retorno-execução@String",38 + "@" + 40);
		map.put("código-identificação-veículo@String",41 + "@" + 61);
		map.put("placa-única@String",62 + "@" + 68);
		map.put("código-RENAVAM@String",69 + "@" + 79);
		map.put("nome-situação-veículo@String",80 + "@" + 89);
		map.put("código-município-emplacamento@String",90 + "@" + 93);
		map.put("UF-jurisdição@String",94 + "@" + 95);
		map.put("tipo-remarcação-chassi@String",96 + "@" + 96);
		map.put("nome-tipo-montagem@String",97 + "@" + 109);
		map.put("código-tipo-veículo@String",110 + "@" + 111);
		map.put("código-marca-modelo@String",112 + "@" + 117);
		map.put("código-espécie-veículo@String",118 + "@" + 118);
		map.put("código-tipo-carroceria@String",119 + "@" + 121);
		map.put("código-cor@String",122 + "@" + 123);
		map.put("ano-modelo@String",124 + "@" + 127);
		map.put("ano-fabricação@String",128 + "@" + 131);
		map.put("potência-veículo@String",132 + "@" + 134);
		map.put("cilindradas@String",135 + "@" + 138);
		map.put("código-combustível@String",139 + "@" + 140);
		map.put("número-motor@String",141 + "@" + 161);
		map.put("CMT-veículo@Decimal@2",162 + "@" + 166);
		map.put("PBT-veículo@Decimal@2",167 + "@" + 171);
		map.put("capacidade-carga@Decimal@2",172 + "@" + 176);
		map.put("nome-procedência-veículo@String",177 + "@" + 187);
		map.put("número-caixa-câmbio@String",188 + "@" + 208);
		map.put("tipo-documento-proprietário@String",209 + "@" + 209);
		map.put("número-identificação-proprietário@String",210 + "@" + 223);
		map.put("número-carroceria@String",224 + "@" + 244);
		map.put("capacidade-passageiros@String",245 + "@" + 247);
		map.put("código-restrição-1@String",248 + "@" + 249);
		map.put("código-restrição-2@String",250 + "@" + 251);
		map.put("código-restrição-3@String",252 + "@" + 253);
		map.put("código-restrição-4@String",254 + "@" + 255);
		map.put("número-eixos@String",256 + "@" + 257);
		map.put("número-eixo-traseiro@String",258 + "@" + 278);
		map.put("número-eixo-auxiliar@String",279 + "@" + 299);
		map.put("tipo-documento-importador@String",300 + "@" + 300);
		map.put("número-identificação-importador@String",301 + "@" + 314);
		map.put("código-órgão-SRF@String",315 + "@" + 321);
		map.put("número-REDA@String",322 + "@" + 326);
		map.put("número-DI@String",327 + "@" + 336);
		map.put("data-registro-DI@Date",337 + "@" + 344);
		map.put("tipo-documento-faturado@String",345 + "@" + 345);
		map.put("número-identificação-faturado@String",346 + "@" + 359);
		map.put("UF-destino-faturamento@String",360 + "@" + 361);
		map.put("data-limite-restrição-tributária@Date",362 + "@" + 369);
		map.put("data-última-atualização@Date",370 + "@" + 377);
		map.put("tipo-operação-importação-veículo@String",378 + "@" + 379);
		map.put("número-processo-importação@String",380 + "@" + 394);
		map.put("data-baixa-transf-outro-país@Date",395 + "@" + 402);
		map.put("codigo-país-transf@String",403 + "@" + 407);
		map.put("indicador-multa-exigível-RENAINF@String",408 + "@" + 408);
		map.put("indicador-comunicação-venda@String",409 + "@" + 409);
		map.put("indicador-pendência-emissão@String",410 + "@" + 410);
		map.put("indicador-restrição-RENAJUD@String",411 + "@" + 411);
		map.put("indicador-ocorrência-recall-1@String",412 + "@" + 413);
		map.put("indicador-ocorrência-recall-2@String",414 + "@" + 415);
		map.put("indicador-ocorrência-recall-3@String",416 + "@" + 417);
		map.put("indicador-ocorrência-recall-4@String",418 + "@" + 419);
		map.put("código-categoria-veículo-MRE@String",420 + "@" + 421);
		map.put("tipo-documento-proprietário-MRE@String",422 + "@" + 422);
		map.put("numero-documento-proprietário-MRE@String",423 + "@" + 436);
		map.put("data-última-atualização-MRE@Date",437 + "@" + 444);
		return map;
	}
}
