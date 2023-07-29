package com.kamauro.mvcudemy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kamauro.mvcudemy.model.Departamento;
import com.kamauro.mvcudemy.service.DepartamentoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService serviceDepartamento;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("departamentos", serviceDepartamento.listarTodos());
        return "/departamento/lista";
    }

    @GetMapping("/cadastrar")
    public String telaCadastrarDepartamento(Departamento departamento) {        
        return "/departamento/cadastro";
    }    

    @GetMapping("editar/{id}")
    public String telaEditarDepartamento(@PathVariable("id") Long id, Model model) {
        model.addAttribute("departamento", serviceDepartamento.buscarPorId(id));
        return "/departamento/cadastro";
    }

    @PostMapping("/salvar")
    public String cadastrarDepartamento(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {
        if(result.hasErrors()) {
            return "/departamento/cadastro";
        }
        
        serviceDepartamento.cadastrar(departamento);
        attr.addFlashAttribute("success", "Registro inserido com sucesso.");
        return "redirect:/departamentos/cadastrar";
    }

    @PostMapping("/editar")
    public String editar(@Valid Departamento departamento, BindingResult result, RedirectAttributes attr) {
        if(result.hasErrors()) {
            return "/departamento/cadastro";
        }
        serviceDepartamento.alterar(departamento.getId(), departamento);
        attr.addFlashAttribute("success", "Registro atualizado com sucesso.");
        return "redirect:/departamentos/cadastrar";
    }

    @GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, Model model) {		
		if (serviceDepartamento.departamentoTemCargos(id)) {
			model.addAttribute("fail", "Registro não removido. Possui cargo(s) vinculado(s).");
		} else {
			serviceDepartamento.deletar(id);
			model.addAttribute("success", "Registro excluído com sucesso.");
		}		
		return listar(model);
	}    
}
