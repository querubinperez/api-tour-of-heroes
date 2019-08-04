package co.udea.heroes.api.service;

import co.udea.heroes.api.model.Hero;

import java.util.List;

public interface HeroServiceInt {

    public List<Hero> getHeroes();
    public Hero getHero(String id);
    public Hero getHeroNo404(String id);
    public Hero addHero(Hero hero);
    public Hero updateHero(Hero hero);
    public void deleteHero(String id);
    public List<Hero> searchHeroes(String term);
    /*public Hero getHeroByName(String name);*/
}
