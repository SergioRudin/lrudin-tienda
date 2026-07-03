package com.tiendaTech.tienda.controller;

import com.tiendaTech.tienda.service.CategoriaService;
import com.tiendaTech.tienda.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ConsultaController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var productos = productoService.getProductos(false);
        var categorias = categoriaService.getCategorias(false);

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);

        return "/consultas/listado";
    }

    @PostMapping("/consultaCategoria")
    public String consultaCategoria(@RequestParam Integer idCategoria,
            @RequestParam double precioInf,
            @RequestParam double precioSup,
            Model model) {

        var lista = productoService.consultaCategoria(idCategoria, precioInf, precioSup);
        var categorias = categoriaService.getCategorias(false);

        model.addAttribute("productos", lista);
        model.addAttribute("categorias", categorias);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        model.addAttribute("idCategoria", idCategoria);

        return "/consultas/listado";
    }
}