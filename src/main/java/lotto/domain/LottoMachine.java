package lotto.domain;

import lotto.constant.Prize;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;
    private final int cashAmount;
    private final LottoNumbers balls;
    private final ProfitRateStrategy profitRateStrategy;
    private final LottoCreateStrategy lottoCreateStrategy;

    private LottoMachine(int cashAmount, ProfitRateStrategy profitRateStrategy, LottoCreateStrategy lottoCreateStrategy) {
        this.cashAmount = cashAmount;
        this.balls = new LottoNumbers();
        this.profitRateStrategy = profitRateStrategy;
        this.lottoCreateStrategy = lottoCreateStrategy;
    }

    public static LottoMachine of(int cashAmount, ProfitRateStrategy profitRateStrategy,  LottoCreateStrategy lottoCreateStrategy) {
        validateCashAmount(cashAmount);
        return new LottoMachine(cashAmount, profitRateStrategy, lottoCreateStrategy);
    }

    private static void validateCashAmount(int cashAmount) {
        if (cashAmount < LOTTO_PRICE) {
            throw new IllegalArgumentException("로또 구입 금액은 최소 " + LOTTO_PRICE + "원 이상이어야 합니다.");
        }
    }

    public List<Lotto> createAutomatically() {
        int purchaseQuantity = calculatePurchaseQuantity();
        return IntStream.range(0, purchaseQuantity)
                .mapToObj(i -> lottoCreateStrategy.create(balls))
                .collect(Collectors.toList());
    }

    public Lotto createManually(Set<Integer> numbers) {
        return new Lotto(numbers);
    }

    public int calculatePurchaseQuantity() {
        return cashAmount / LOTTO_PRICE;
    }

    public EnumMap<Prize, Integer> checkLottoPrize(List<Lotto> lottoList, Set<Integer> winningNumbers, int bonusNumber) {
        EnumMap<Prize, Integer> countMap = new EnumMap<>(Prize.class);
        for (Lotto lotto : lottoList) {
            int count = match(lotto, winningNumbers);
            Prize prize = Prize.calculateRank(count, isBonusNumberMatch(lotto, bonusNumber));
            countMap.merge(prize, 1, Integer::sum);
        }
        return countMap;
    }

    private int match(Lotto lotto, Set<Integer> winningNumbers) {
        return lotto.getNumbers().stream()
                .filter(winningNumbers::contains)
                .collect(Collectors.toList()).size();
    }

    private boolean isBonusNumberMatch(Lotto lotto, int bonusNumber) {
        return lotto.getNumbers().contains(bonusNumber);
    }

    public String calculateProfitRate(EnumMap<Prize, Integer> countMap) {
        return profitRateStrategy.calculateProfitRate(countMap, cashAmount);
    }

}
