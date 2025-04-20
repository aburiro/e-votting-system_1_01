package com.example.e_votting_system_1;

import com.example.e_votting_system_1.contracts.VotingSystem;

import org.web3j.tx.gas.StaticGasProvider;
import java.math.BigInteger;
import android.content.Context;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import java.math.BigInteger;

public class BlockchainService {
    private static final String CONTRACT_ADDRESS = "0x079Ace289848447C77865928Dba2bf9695F05546";

    private static final String PRIVATE_KEY = "d59109aba710888dc14618d580d31aa0f34ea12e562ff41b2c4997ec319420e2";

    private static final String INFURA_URL = "http://10.102.139.5:7545";


    private Web3j web3j;
    private VotingSystem contract;
    private Credentials credentials;



    public BlockchainService(Context context) {
        web3j = Web3j.build(new HttpService(INFURA_URL));
        credentials = Credentials.create(PRIVATE_KEY);

        // 👇 Custom gas values
        BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L); // 20 Gwei
        BigInteger GAS_LIMIT = BigInteger.valueOf(6_700_000L); // Should match Ganache block gas limit

        // ✅ Load the contract with custom gas
        contract = VotingSystem.load(
                CONTRACT_ADDRESS,
                web3j,
                credentials,
                new StaticGasProvider(GAS_PRICE, GAS_LIMIT)
        );
    }

    public VotingSystem getContract() {
        return contract;
    }

    // Method to cast vote
    public void castVote(BigInteger candidateId, BlockchainCallback callback) {
        new Thread(() -> {
            try {
                // Send transaction to blockchain
                contract.vote(candidateId).send();
                callback.onSuccess();
            } catch (Exception e) {
                callback.onError(e.getMessage());
            }
        }).start();
    }

    // Callback interface
    public interface BlockchainCallback {
        void onSuccess();
        void onError(String error);
    }
}