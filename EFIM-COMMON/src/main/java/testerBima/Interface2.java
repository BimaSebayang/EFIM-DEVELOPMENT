package testerBima;

public interface Interface2 {
 String talk();
 default String whisper() {
	 return "yes";
 }
}
