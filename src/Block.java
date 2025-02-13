import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class Block {
    public String hash;
    public String previousHash;
    private final String data;
    private final long timeStamp;
    private int nonce;
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = System.currentTimeMillis();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return StringUtil.applySha256(previousHash + timeStamp + nonce + data);
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        AtomicBoolean found = new AtomicBoolean(false);

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadNonce = i;
            executor.execute(() -> {
                int localNonce = threadNonce;
                while (!found.get()) {
                    String newHash = StringUtil.applySha256(previousHash + timeStamp + localNonce + data);
                    if (newHash.startsWith(target)) {
                        synchronized (this) {
                            if (!found.get()) {
                                found.set(true);
                                nonce = localNonce;
                                hash = newHash;
                                System.out.println("Block Mined!!! : " + hash);
                            }
                        }
                        break;
                    }
                    localNonce += THREAD_COUNT; // Each thread increments nonce differently
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait for all threads to finish
        }
    }
}