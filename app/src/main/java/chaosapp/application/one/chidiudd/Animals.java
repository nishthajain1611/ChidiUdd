package chaosapp.application.one.chidiudd;

/**
 * Created by nishtha jain on 14-05-2016.
 */
public class Animals {
    // index 4x are non flying      3x-1
    private static String name[]={
            "sparrow","eagle","Mr Bean",
            "clouds","airplane","dog",
            "kingfisher","woodpecker","Johnny Bravo",
            "time","superman","lion",
            "owl","cuckoo bird","tiger",
            "boomerang","parrot","ostrich",
            "pigeon","crow","kiwi bird",
            "space ship","raven","cow",
            "peacock","UFO","rhea bird",
            "power puff girls","Aladdin's carpet","scare crow",
            "rocket","butterfly","Titanic",
            "spider man","bee","iphone",
            "Harry Potter","vampire bat","Taj Mahal",
            "para glider","doraemon","mouse",
            "dove","vulture","laptop",
            "dust particles","parachute","penguin",
            "hot air balloon","pegasus","Jackie Chan",
            "locust","Krrish","caterpillar",
            "kite","helicopter","mango",
            "turkey","dragons","pikachu",
            "Goku","angels","blue whale",
            "dragonfly","Tinker Bell","Lady Gaga",
            "witch","paper plane","crocodile",
            "firefly","batman","paper boat",
            "swan","flamingo","pine cone",
            "wasp","satellite","elephant",
            "Peter Pan","house fly","chair",
            "moth","mosquito","squirrel",
            "lady bug","beetle","donkey",
            "cricket","flying fish","yourself",
            "flying saucer","fighter jet","Donald Trump",
            "drones","sky lantern","giraffe"

    };
    public static String getName(int index){
        return name[index];
    }

    public static int getSize(){
        return name.length;
    }
}
