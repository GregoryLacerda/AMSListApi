package com.gregory.AMSList.domain.enums;

public enum Profile {
	
	ADMIN(0, "ROLE_ADMIN"), USER(1, "ROLE_USER");
	
	private Integer codigo;
	private String descricao;
	
	private Profile(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Profile toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(Profile x : Profile.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Perfil inválido");
	}
}
