package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class WalletTest {

    @Test
    public void newWalletIsEmpty() throws Exception {
        // Given / Arrange
        Wallet wallet = new Wallet();
        // When  / Act

        // Then  / Assert
        assertThat(wallet.isEmpty())
                .isTrue();
    }

    @Test
    public void newWalletAddMoneyIsNotEmpty() throws Exception {
        Wallet wallet = new Wallet();

        wallet.addMoney(1);

        assertThat(wallet.isEmpty())
                .isFalse();
    }

    @Test
    public void newWalletShouldHaveBalanceOfZero() throws Exception {
        Wallet wallet = new Wallet();

        assertThat(wallet.balance())
                .isZero();
    }

    @Test
    public void newWalletAdd10ThenBalanceIs10() throws Exception {
        Wallet wallet = new Wallet();

        wallet.addMoney(10);

        assertThat(wallet.balance())
                .isEqualTo(10);
    }

    @Test
    public void newWalletAdd7AndAdd8ThenBalanceIs15() throws Exception {
        Wallet wallet = new Wallet();

        wallet.addMoney(7);
        wallet.addMoney(8);

        assertThat(wallet.balance())
                .isEqualTo(7 + 8); // EVIDENT DATA
    }

    @Test
    public void addMoneyOfNegativeAmountThrowsException() throws Exception {
        Wallet wallet = new Wallet();

        assertThatThrownBy(() -> {
            wallet.addMoney(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void addMoneyOfZeroThrowsException() throws Exception {
        Wallet wallet = new Wallet();

        assertThatThrownBy(() -> {
            wallet.addMoney(0);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
