package co.udea.heroes.api.controller;

import co.udea.heroes.api.model.Hero;
import co.udea.heroes.api.service.HeroServiceInt;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tourofheroes", produces = MediaType.APPLICATION_JSON_VALUE)
public class HeroController {

    private static Logger log = LoggerFactory.getLogger(HeroController.class);
    private HeroServiceInt heroService;

    public HeroController(HeroServiceInt heroService) {
        this.heroService = heroService;
    }

    @GetMapping("listar")
    @ApiOperation(value = "Lista todos los heroes", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Los heroes fueron listados", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<List<Hero>> getHeroes(){
        log.debug("REST request listar todos los heroes");
        return ResponseEntity.ok(heroService.getHeroes());
    }

    @GetMapping(value = "consultar/{id}")
    @ApiOperation(value = "Consultar heroe por id", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe encontrado", response = Hero.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> getHero(@PathVariable("id") String id){
        log.debug("REST request getHero id : {}", id);
        return ResponseEntity.ok(heroService.getHero(id));
    }

    @GetMapping("/consultar404")
    @ApiOperation(value = "Consultar heroe por id. Error 404 si no lo encuentra", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe encontrado", response = Hero.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> getHeroNo404(@RequestParam("id") String id){
        return ResponseEntity.ok(heroService.getHeroNo404(id));
    }

    @GetMapping("/buscar")
    @ApiOperation(value = "Buscar todos los heroes por termino indicado", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Los heroes fueron buscados", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<List<Hero>> searchHeroes(@RequestParam("term") String term) {
        return ResponseEntity.ok(heroService.searchHeroes(term));
    }

    @PostMapping("/crear")
    @ApiOperation(value = "Crear heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe creado", response = Hero.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> addHero(@RequestBody Hero hero) {
        return ResponseEntity.ok(heroService.addHero(hero));
    }

    @PutMapping("/actualizar")
    @ApiOperation(value = "Actualizar heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe actualizado", response = Hero.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> updateHero(@RequestBody Hero hero) {
        return ResponseEntity.ok(heroService.updateHero(hero));
    }


    @DeleteMapping("/borrar/{id}")
    @ApiOperation(value = "Eliminar heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe Eliminado", response = Void.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public void deleteHero(@PathVariable("id") String id) {
        heroService.deleteHero(id);
    }

    /*
    @GetMapping("name/{name}")
    public ResponseEntity<Hero> getHeroByName(@PathVariable("name") String name){
        return ResponseEntity.ok(heroService.getHeroByName(name));
    }
    */

}
