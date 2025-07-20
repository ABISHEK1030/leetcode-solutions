class Solution {
    static class TrieNode {
        String name;
        Map<String, TrieNode> children = new HashMap<>();
        boolean isDeleted = false;
        TrieNode(String name) { this.name = name; }
      
    }
    Map<String, List<TrieNode>> serialMap = new HashMap<>();
    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
         TrieNode root = new TrieNode("");
       
        for (List<String> p : paths) {
            TrieNode cur = root;
            for (String folder : p) {
                cur.children.putIfAbsent(folder, new TrieNode(folder));
                cur = cur.children.get(folder);
            }
        }
        
        serialize(root);
        for (List<TrieNode> group : serialMap.values()) {
            if (group.size() > 1) {
                for (TrieNode node : group) {
                    node.isDeleted = true;
                }
            }
        }
     
        List<List<String>> ans = new ArrayList<>();
        dfs(root, new ArrayList<>(), ans);
        return ans;
    }

    private String serialize(TrieNode node) {
        if (node.children.isEmpty()) return "";
        List<String> parts = new ArrayList<>();
        for (String cname : node.children.keySet().stream().sorted().toList()) {
            TrieNode child = node.children.get(cname);
            String sub = serialize(child);
            parts.add(cname + "[" + sub + "]");
        }
        String serial = String.join("", parts);
        serialMap.computeIfAbsent(serial, k -> new ArrayList<>()).add(node);
        return serial;
    }

    private void dfs(TrieNode node, List<String> path, List<List<String>> sol) {
        for (TrieNode child : node.children.values()) {
            if (!child.isDeleted) {
                path.add(child.name);
                sol.add(new ArrayList<>(path));
                dfs(child, path, sol);
                path.remove(path.size() - 1);
            }
        }
        
    }
}