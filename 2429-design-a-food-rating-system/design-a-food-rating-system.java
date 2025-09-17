import java.util.*;

class FoodRatings {
    // Store current rating of each food
    private final Map<String, Integer> foodToRating = new HashMap<>();
    // Store cuisine of each food
    private final Map<String, String> foodToCuisine = new HashMap<>();
    // For each cuisine, keep foods ordered by (rating desc, name asc)
    private final Map<String, TreeSet<String>> cuisineToFoods = new HashMap<>();

    // Helper: comparator based on rating first (desc), then lexicographic name (asc)
    private final Comparator<String> comp = (a, b) -> {
        int ra = foodToRating.get(a);
        int rb = foodToRating.get(b);
        if (ra != rb) return Integer.compare(rb, ra); // higher rating first
        return a.compareTo(b); // tie → smaller name first
    };

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        for (int i = 0; i < foods.length; i++) {
            foodToRating.put(foods[i], ratings[i]);
            foodToCuisine.put(foods[i], cuisines[i]);
            cuisineToFoods
                .computeIfAbsent(cuisines[i], k -> new TreeSet<>(comp))
                .add(foods[i]);
        }
    }

    public void changeRating(String food, int newRating) {
        String cuisine = foodToCuisine.get(food);
        TreeSet<String> set = cuisineToFoods.get(cuisine);
       
        set.remove(food);
        foodToRating.put(food, newRating);
        set.add(food);
    }

    public String highestRated(String cuisine) {
        
        return cuisineToFoods.get(cuisine).first();
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */
