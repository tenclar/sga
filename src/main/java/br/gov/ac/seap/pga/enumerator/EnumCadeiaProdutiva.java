package br.gov.ac.seap.pga.enumerator;

public enum EnumCadeiaProdutiva {
		LEITE, MAMAO, BANANA, CAFE, MILHO;
		
		public String getDescricao(){
			
			switch (this) {
			case LEITE: return "Leite";				
			case MAMAO: return "Mamão";
			case BANANA: return "Banana";
			case CAFE: return "Milho";
			default: return "";
				
			}
		}
	
	
	
	
	
	
	

}
