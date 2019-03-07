package com.maog.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
	
	private String url;
	private Page<T> page;
	
	private int totalPaginas;
	private int numElementosPorPaginas;
	private int paginaActual;
	private List<PageItem> paginas;
	
	public PageRender(String url, Page<T> page) {
		this.url = url;
		this.page = page;
		this.paginas = new ArrayList<>();
		
		this.numElementosPorPaginas = page.getSize();
		this.totalPaginas = page.getTotalPages();
		this.paginaActual = page.getNumber()+1;
		
		int desde,hasta;
		if(totalPaginas <= numElementosPorPaginas) {
			desde = 1;
			hasta = totalPaginas;
		}else {
			if(paginaActual<= numElementosPorPaginas/2) {
				desde = 1;
				hasta = numElementosPorPaginas;
			}else if (paginaActual >= totalPaginas - numElementosPorPaginas) {
				desde = totalPaginas -numElementosPorPaginas + 1;
				hasta = numElementosPorPaginas;
				
			}else {
				desde = paginaActual - numElementosPorPaginas/2;
				hasta = numElementosPorPaginas;
				
			}
		}
		
		for(int i=0;i<hasta;i++) {
			paginas.add(new PageItem(desde +1, paginaActual == desde +1));
		}
	}

	public String getUrl() {
		return url;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}
	
	public boolean isFirst() {
		return page.isFirst();
	}
	
	public boolean isLast() {
		return page.isLast();
	}
	
	public boolean isHasNext() {
		return page.hasNext();
	}
	
	public boolean isHastPrevius() {
		return page.hasPrevious();
	}

}
