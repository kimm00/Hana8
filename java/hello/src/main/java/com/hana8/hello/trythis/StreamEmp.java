package com.hana8.hello.trythis;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class StreamEmp {

	String name;
	String dept;
	int score;

//	StreamEmp(String name, String dept, int score) {
//		this.name = name;
//		this.dept = dept;
//		this.score = score;
//	}

	@Override
	public String toString() {
		return "%s (%d)".formatted(name, score);
	}

	public void print() {
		System.out.printf("%s: %s(%d)", dept, name, score);
	}

	public void println() {
		System.out.printf("%s: %s(%d)%n", dept, name, score);
	}

//	public String getName() {
//		return name;
//	}
//
//	public String getDept() {
//		return dept;
//	}
//
//	public int getScore() {
//		return score;
//	}

	public static void main(String[] args) {
		List<StreamEmp> emps = Arrays.asList(
				new StreamEmp("Hong", "Sales", 85),
				new StreamEmp("Kim", "Sales", 95),
				new StreamEmp("Choi", "HR", 55),
				new StreamEmp("Nam", "HR", 75),
				new StreamEmp("Lee", "IT", 82),
				new StreamEmp("Park", "IT", 92),
				new StreamEmp("Ahn", "Sales", 95)
		);

		// 1. 고과 점수가 70점 미만인 사람은 제외
		List<StreamEmp> candidates = emps.stream().filter(emp -> emp.getScore() >= 70).toList();
		candidates.forEach(System.out::println);
		System.out.println("1. 고과 점수가 70점 미만인 사람은 제외");
		candidates.forEach(StreamEmp::println);

		// 2. 남은 사람들은 부서별로 출력 (부서 이름 순)
		var empsByDept = candidates.stream()
				.sorted(Comparator.comparing(StreamEmp::getDept, String::compareTo))
				.collect(Collectors.groupingBy(StreamEmp::getDept, LinkedHashMap::new,
						Collectors.toList()));
		System.out.println("2. 남은 사람들은 부서별로 출력 (부서 이름 순)");
		System.out.println(empsByDept);

		// 3.부서 별 최고 점수 1명만 남기기
		// (점수가 같다면 이름이 빠른 사람 한명만 선택 Aha, Nam, Park)
		LinkedHashMap<String, Optional<StreamEmp>> maxScoreByDept = candidates.stream()
				.sorted(Comparator.comparing(StreamEmp::getName))
				.sorted(Comparator.comparing(StreamEmp::getDept))
				.collect(Collectors.groupingBy(StreamEmp::getDept, LinkedHashMap::new,
						Collectors.maxBy(Comparator.comparingInt(StreamEmp::getScore))));
		System.out.println("3.부서 별 최고 점수 1명만 남기기");
		System.out.println(maxScoreByDept);

		// 4. 부서 이름 역순으로 출력   - 출력 예) 부서명: 이름(점수)
		LinkedHashMap<String, Optional<StreamEmp>> maxScoreByDeptOrder = candidates.stream()
				.sorted(Comparator.comparing(StreamEmp::getDept).reversed())
				.sorted(Comparator.comparing(StreamEmp::getName))
				.collect(Collectors.groupingBy(StreamEmp::getDept, LinkedHashMap::new,
						Collectors.maxBy(Comparator.comparing(StreamEmp::getScore))));
		System.out.println("4. 부서 이름 역순으로 출력   - 출력 예) 부서명: 이름(점수)");
		System.out.println(maxScoreByDeptOrder);
		System.out.println("-----------------------");

		Set<Map.Entry<String, Optional<StreamEmp>>> entries = maxScoreByDeptOrder.entrySet();
		for (Map.Entry<String, Optional<StreamEmp>> entry : entries) {
			String dept = entry.getKey();
			StreamEmp tEmp = entry.getValue().orElse(null);
			if (tEmp == null) {
				System.out.printf("%s: 최고 득접자 없음!%n", dept);
			} else {
				tEmp.println();
			}
		}

		System.out.println(" 1. 고과 점수가 70점 미만인 사람은 제외");
		List<StreamEmp> filtered = emps.stream().filter(i -> i.getScore() >= 70).toList();
		System.out.println(filtered);
		filtered.stream().map(StreamEmp::getName).forEach(System.out::println);
		System.out.println();

		System.out.println(" 2. 남은 사람들은 부서별로 출력 (부서 이름 순)");
		filtered.stream().sorted(Comparator.comparing(StreamEmp::getDept))
				.forEach(System.out::println);
		System.out.println();

		System.out.println(" 3. 부서 별 최고 점수 1명만 남기기\n"
				+ "(점수가 같다면 이름이 빠른 사람 한명만 선택)");
		// 부서별 그룹핑
		Map<String, List<StreamEmp>> grouped =
				filtered.stream().collect(Collectors.groupingBy(StreamEmp::getDept));
		// 큰 점수부터 내림차순 후 맨 위에 오는 것 찾기
		Map<String, StreamEmp> result =
				grouped.entrySet().stream()
						.collect(Collectors.toMap(Map.Entry::getKey,
								entry -> entry.getValue().stream()
										.sorted(Comparator.comparing(StreamEmp::getScore)
												.reversed()
												.thenComparing(StreamEmp::getName)).findFirst()
										.get()));
		result.values().forEach(System.out::println);
		System.out.println();

		System.out.println(" 4. 부서 이름 역순으로 출력   출력 예) 부서명: 이름(점수)");
		filtered.stream().sorted(Comparator.comparing(StreamEmp::getDept).reversed())
				.forEach(e -> System.out.println(
						e.getDept() + ": " + e.getName() + "(" + e.getScore() + ")"));
	}
}
