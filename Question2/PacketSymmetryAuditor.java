public class PacketSymmetryAuditor {
    public static void main(String[] args) {
        String packet = "racecar";
        TransactionStack stack = new TransactionStack(packet.length());
        RequestQueue queue = new RequestQueue(packet.length());

        for (char c : packet.toCharArray()) {
            stack.push(c);
            queue.enqueue(c);
        }

        boolean isSymmetric = true;
        while (!stack.isEmpty() && !queue.isEmpty()) {
            if (stack.pop() != queue.dequeue()) {
                isSymmetric = false;
                break;
            }
        }

        if (isSymmetric) {
            System.out.println("Terminal Log: Packet exhibits bi-directional symmetry (Palindrome Verified).");
        } else {
            System.out.println("Terminal Log: Packet is NOT symmetric.");
        }
    }
}