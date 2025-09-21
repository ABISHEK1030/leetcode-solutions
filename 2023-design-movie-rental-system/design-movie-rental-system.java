import java.util.*;

class MovieRentingSystem {

    private final Map<Integer, TreeSet<int[]>> movieToShops;
 
    private final TreeSet<int[]> rented;
  
    private final Map<String, Integer> priceMap;

    public MovieRentingSystem(int n, int[][] entries) {
        movieToShops = new HashMap<>();
        rented = new TreeSet<>((a, b) ->
            a[0] != b[0] ? a[0] - b[0] :
            a[1] != b[1] ? a[1] - b[1] : a[2] - b[2]);
        priceMap = new HashMap<>();

        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            movieToShops
                .computeIfAbsent(movie, k -> new TreeSet<>(
                    (x, y) -> x[0] != y[0] ? x[0] - y[0] : x[1] - y[1]))
                .add(new int[]{price, shop});
            priceMap.put(shop + "_" + movie, price);
        }
    }

  
    public List<Integer> search(int movie) {
        List<Integer> res = new ArrayList<>();
        if (!movieToShops.containsKey(movie)) return res;
        Iterator<int[]> it = movieToShops.get(movie).iterator();
        while (it.hasNext() && res.size() < 5) {
            res.add(it.next()[1]);
        }
        return res;
    }

   
    public void rent(int shop, int movie) {
        int price = priceMap.get(shop + "_" + movie);
        movieToShops.get(movie).remove(new int[]{price, shop});
        rented.add(new int[]{price, shop, movie});
    }

  
    public void drop(int shop, int movie) {
        int price = priceMap.get(shop + "_" + movie);
        rented.remove(new int[]{price, shop, movie});
        movieToShops
            .computeIfAbsent(movie, k -> new TreeSet<>(
                (x, y) -> x[0] != y[0] ? x[0] - y[0] : x[1] - y[1]))
            .add(new int[]{price, shop});
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        Iterator<int[]> it = rented.iterator();
        while (it.hasNext() && res.size() < 5) {
            int[] cur = it.next();
            res.add(Arrays.asList(cur[1], cur[2]));
        }
        return res;
    }
}

/**
 * Usage:
 * MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 * List<Integer> s = obj.search(movie);
 * obj.rent(shop, movie);
 * obj.drop(shop, movie);
 * List<List<Integer>> r = obj.report();
 */
