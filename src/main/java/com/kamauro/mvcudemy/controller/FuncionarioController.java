package com.kamauro.mvcudemy.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kamauro.mvcudemy.model.Cargo;
import com.kamauro.mvcudemy.model.Funcionario;
import com.kamauro.mvcudemy.model.UF;
import com.kamauro.mvcudemy.service.CargoService;
import com.kamauro.mvcudemy.service.FuncionarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService serviceFuncionario;

    @Autowired
    private CargoService serviceCargo;

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("funcionarios", serviceFuncionario.listarTodos());
        return "/funcionario/lista";
    }

    @GetMapping("/cadastrar")
    public String telaCadastrarFuncionario(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        model.addAttribute("edicao", false);
        return "/funcionario/cadastro";
    }

    @RequestMapping(value = "/cadastrarFuncionario", params = { "cadastrar" })
    public String cadastrarFuncionario(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
        if(result.hasErrors()) {
            return "/funcionario/cadastro";
        }
        
        serviceFuncionario.cadastrar(funcionario);
        attr.addFlashAttribute("success", "Registro inserido com sucesso.");
        return "redirect:/funcionarios/cadastrar";
    }

     @GetMapping("/editar/{id}")
    public String telaEditarFuncionario(@PathVariable("id") Long id, Model model) {
        Funcionario funcionarioBanco = serviceFuncionario.buscaPorId(id).get();
        model.addAttribute("funcionario", funcionarioBanco);
        model.addAttribute("cargos", funcionarioBanco.getCargo());
        model.addAttribute("edicao", true);
        return "/funcionario/cadastro";
    }

    @RequestMapping(value = "/cadastrarFuncionario", params = { "atualizar" })
    public String editar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
        if(result.hasErrors()) {
            return "/funcionario/cadastro";
        }
        serviceFuncionario.alterar(funcionario.getId(), funcionario);
        attr.addFlashAttribute("success", "Registro salvo com sucesso.");
        return "redirect:/funcionarios/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
        serviceFuncionario.deletar(id);
        attr.addFlashAttribute("success", "Registro excluido com sucesso");
        return "/funcionario/lista";
    }	

    @ModelAttribute("cargos")
	public List<Cargo> getCargos() {
		return serviceCargo.listarTodos();
	}

    @ModelAttribute("ufs")
    public UF[] getUfs() {
        return UF.values();
    }
    
}
