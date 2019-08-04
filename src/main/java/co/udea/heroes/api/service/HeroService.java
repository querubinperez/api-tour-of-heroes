package co.udea.heroes.api.service;

import co.udea.heroes.api.exception.DataNotFoundException;
import co.udea.heroes.api.model.Hero;
import co.udea.heroes.api.repository.HeroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HeroService implements HeroServiceInt {

    private final Logger log = LoggerFactory.getLogger(HeroService.class);
    private HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public List<Hero> getHeroes(){
        return heroRepository.findAll();
    }

    @Override
    public Hero getHero(String id) {
        Optional<Hero> posibleHero = heroRepository.findById(id);
        if(posibleHero.isPresent()){
            return posibleHero.get();
        }else {
            log.error("No existe un heroe con ese id");
            throw new DataNotFoundException("No existe un heroe con id: "+ id);        }
    }

    @Override
    public Hero getHeroNo404(String id) {
        Optional<Hero> posibleHero = heroRepository.findById(id);
        if(posibleHero.isPresent()){
            return posibleHero.get();
        }else {
            log.error("No existe un heroe con ese id. 404 Not Found");
            throw new DataNotFoundException("No existe un heroe con id: "+ id +". Error 404 Not Found");        }
    }

    @Override
    public Hero updateHero(Hero hero) {
        return heroRepository.save(hero);
    }

    @Override
    public Hero addHero(Hero hero) {
        return heroRepository.save(hero);
    }

    @Override
    public void deleteHero(String id) {
        Optional<Hero> posibleHero = heroRepository.findById(id);
        if(posibleHero.isPresent()){
            heroRepository.delete(posibleHero.get());
        }else {
            log.error("No existe un heroe con ese id");
            throw new DataNotFoundException("No existe un heroe con id: "+ id);}
    }

    @Override
    public List<Hero> searchHeroes(String term) {
        return heroRepository.findByName(term);
    }

    /*
    @Override
    public Hero getHeroByName(String name){
        Optional<Hero> posibleHero = heroRepository.findByName(name);
        if(posibleHero.isPresent()){
            return posibleHero.get();
        }else {
            return null;
        }
    }
    */
}
