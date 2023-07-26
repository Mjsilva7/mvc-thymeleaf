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
import com.kamauro.mvcudemy.model.Departamento;
import com.kamauro.mvcudemy.service.CargoService;
import com.kamauro.mvcudemy.service.DepartamentoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cargos")
public class CargoController {

    @Autowired
    private CargoService serviceCargo;

    @Autowired
    private DepartamentoService serviceDepartamento;

	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("cargos", serviceCargo.listarTodos());
		return "/cargo/lista"; 
	}

    @GetMapping("/cadastrar")
	public String telaCadastrarCargo(Model model) {
		model.addAttribute("cargo", new Cargo());
		model.addAttribute("edicao", false);
		return "/cargo/cadastro";
	}	
	
	@RequestMapping(value = "/cadastrarCargo", params = { "cadastrar" })
	public String cadastrarCargo(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "/cargo/cadastro";
		}

		serviceCargo.cadastrar(cargo);
		attr.addFlashAttribute("success", "Registro inserido com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String telaEditarCargo(@PathVariable("id") Long id, Model model) {
		Cargo cargoBanco = serviceCargo.buscaPorId(id).get();
		model.addAttribute("departamento", cargoBanco.getDepartamento());
		model.addAttribute("cargo", cargoBanco);
		model.addAttribute("edicao", true);
		return "cargo/cadastro";
	}
	
	@RequestMapping(value = "/cadastrarCargo", params = { "atualizar" })
	public String editar(@Valid Cargo cargo, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "/cargo/cadastro";
		}
		serviceCargo.alterar(cargo.getId(), cargo);
		attr.addFlashAttribute("success", "Registro atualizado com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		if (serviceCargo.cargoTemFuncionarios(id)) {
			attr.addFlashAttribute("fail", "Registro não excluido. Tem funcionário(s) vinculado(s).");
		} else {
			serviceCargo.deletar(id);
			attr.addFlashAttribute("success", "Registro excluido com sucesso.");
		}
		return "redirect:/cargos/listar";
	}
	
	@ModelAttribute("departamentos")
	public List<Departamento> listaDeDepartamentos() {
		return serviceDepartamento.listarTodos();
	}	
}
