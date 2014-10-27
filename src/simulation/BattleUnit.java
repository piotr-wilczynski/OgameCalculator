
package simulation;

import java.util.Random;

public class BattleUnit {
    private final Unit_Enum unit;
    private float Hull_Plating;
    private float Shield_Strength;
    private float Attack_Strength;
    private Random random;

    public BattleUnit(Unit_Enum unit,Technologies tech) {
        this.unit = unit;
        Hull_Plating = unit.getHull_Plating(tech);
        Shield_Strength = unit.getShield_Strength(tech);
        Attack_Strength = unit.getAttack_Strength(tech);
        random = new Random();
    }

    public BattleUnit(Unit_Enum unit, float Hull_Plating, float Shield_Strength, float Attack_Strength) {
        this.unit = unit;
        this.Hull_Plating = Hull_Plating;
        this.Shield_Strength = Shield_Strength;
        this.Attack_Strength = Attack_Strength;
        random = new Random();
    }
    
    
    
    public boolean Fight(BattleUnit unit,Technologies tech){
        //If the Weaponry of the shooting unit is less than 1% of the Shielding of the target unit, the shot is bounced, and the target unit does not lose anything (i.e. shot is wasted).
        if(Attack_Strength<unit.getShield_Strength()/100){
        }else if(Attack_Strength<unit.getShield_Strength()){
            unit.setShield_Strength(unit.getShield_Strength()-Attack_Strength);
        }else{
            float hull = unit.getHull_Plating()+unit.getShield_Strength()-(Attack_Strength);
            if(hull<0)
                hull=0;
            unit.setHull_Plating(hull);
            unit.setShield_Strength(0);
            float initial_hull = unit.getUnit().getHull_Plating(tech);
            if(unit.getHull_Plating()<0.7f*initial_hull&&hull>0){
                 //if(rand() % 100 >= 100.f * obj->Life / MaxLifes[DefferID][ZielTeam][obj->Type])
                if(random.nextInt(100)>=100*(unit.getHull_Plating()/initial_hull)){
                    unit.setHull_Plating(0);
                }
            }              
        }
        int rapidfire;
        if((rapidfire=this.unit.getRapidfire(unit.getUnit()))>0){
            float probability_to_shot_again = (1.0f*(rapidfire-1))/rapidfire;
            if(probability(probability_to_shot_again)){
                return true;
            }
        }
        return false;
    }

    public float getHull_Plating() {
        return Hull_Plating;
    }

    public float getShield_Strength() {
        return Shield_Strength;
    }

    public float getAttack_Strength() {
        return Attack_Strength;
    }

    public Unit_Enum getUnit() {
        return unit;
    }    

    public void setHull_Plating(float Hull_Plating) {
        this.Hull_Plating = Hull_Plating;
    }

    public void setShield_Strength(float Shield_Strength) {
        this.Shield_Strength = Shield_Strength;
    }

    public void setAttack_Strength(float Attack_Strength) {
        this.Attack_Strength = Attack_Strength;
    }
    
    public boolean isDestroyed(){
        if(Hull_Plating<=0)
            return true;
        else
            return false;
    }
    public void renewShield(Technologies tech){
        Shield_Strength = unit.getShield_Strength(tech);
    }

    @Override
    public String toString() {
        return Hull_Plating+" "+Shield_Strength+" "+Attack_Strength;
    }

    @Override
    protected BattleUnit clone(){
        return new BattleUnit(unit, Hull_Plating, Shield_Strength, Attack_Strength);
    }
    
    
    public boolean probability(float chance){
        int r = random.nextInt(1000);
        if(r<(1000*chance))//<=
            return true;
        else
            return false;
    }  
    
    
    
}
