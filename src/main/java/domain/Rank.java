package domain;

import java.util.Arrays;

public enum Rank {

	FIRST(6, 2_000_000_000,1),
	SECOND(5, 30_000_000,2),
	THIRD(5, 1_500_000,3),
	FOURTH(4, 50_000,4),
	FIFTH(3, 5_000,5),
	MISS(0, 0,0);

	private int matchNumber;
	private int amount;
	private int ranking;

	Rank(int matchNumber, int amount, int ranking) {
		this.matchNumber = matchNumber;
		this.amount = amount;
		this.ranking = ranking;
	}

	public static Rank rank(int matchNumber, boolean bonusStatus) {

		if (matchNumber == Rank.SECOND.matchNumber && bonusStatus) {
			return SECOND;
		}

		if (matchNumber == Rank.THIRD.matchNumber) {
			return THIRD;
		}

		return Arrays.stream(values())
						.filter(rank -> Rank.match(rank, matchNumber))
						.findFirst()
						.orElse(MISS);
	}

	public static boolean match(Rank rank, int matchNumber) {
		return rank.matchNumber == matchNumber;
	}

	public Integer getAmount() {
		return amount;
	}

	public Integer getRanking() {
		return ranking;
	}
}
