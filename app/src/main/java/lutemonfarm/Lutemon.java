package lutemonfarm;

public class Lutemon {

    protected String name, color;
    protected int attack, defense, experience, health, maxHealth, id, wins, losses, statement;
    private static int idCounter = 1;

    public Lutemon(String name, String color, int attack, int defense, int experience, int maxHealth) {
        this.name = name;
        this.color = color;
        this.attack = attack;
        this.defense = defense;
        this.experience = experience;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.wins = 0;
        this.losses = 0;
        id = getNewId();
        this.statement = 0;
    }


    private int getNewId() { // Give lutemons id and grow for one every time
        return idCounter++;
    }

    public int getId() { // Get lutemons id
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getExperience() {
        return experience;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public String goToTraining(Lutemon lutemon) {
        // Check first that Training area id free to go


        return "";
    }
}
