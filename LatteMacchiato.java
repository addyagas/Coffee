public class LatteMacchiato extends Coffee{
    public LatteMacchiato(String type, boolean isIced){
        super("Latte Macchiato"+" - "+ type+(isIced?" - Iced":""));
    }
}
