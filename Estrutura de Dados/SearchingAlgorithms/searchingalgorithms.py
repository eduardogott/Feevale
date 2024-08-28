# [HOBBY]

import os
import time
import random

def clear():
    os.system('cls' if os.name == 'nt' else 'clear')

def time_it(func):
    def wrapper(arr, target = -1, *args, **kwargs):
        start_time = time.perf_counter_ns()
        if target != -1:
            result = func(arr, target, *args, **kwargs)
        else:
            result = func(arr, *args, **kwargs)
        end_time = time.perf_counter_ns()
        execution_time = end_time - start_time
        return result, execution_time
    return wrapper

@time_it
def generate_array(array_size):
    arr = []
    for i in range(array_size):
        if i % 10_000_000 == 0:
            clear()
            print(f"Gerando array (vetor)... {i}/{array_size}")
        arr.append(i)
    return arr
    
    
@time_it
def sequential_search(arr, target):
    for i in range(len(arr)):
        if arr[i] == target:
            return i
    return -1

@time_it
def binary_search(arr, target):
    left = 0
    right = len(arr) - 1
    while left <= right:
        mid = (left + right) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            left = mid + 1
        else:
            right = mid - 1
    return -1
    
def main():
    times_to_run = 10
    array_size = 200_000_000
    
    arr, time_to_generate = generate_array(array_size)
    time_to_generate_millis = time_to_generate / 1_000_000.0
    
    search_times = {"sequential": [], "binary": [],
                    "largest_num": {"num": 0, "seq_time": {"nano": 0, "millis": 0}, "bin_time": {"nano": 0, "millis": 0}},
                    "smallest_num": {"num": float("inf"), "seq_time": {"nano": 0, "millis": 0}, "bin_time": {"nano": 0, "millis": 0}},
                    "sum": {"seq": 0, "bin": 0}, "averages": {"seq": {"nano": 0, "millis": 0}, "bin": {"nano": 0, "millis": 0}},
                    "differences": {"nano": 0, "millis": 0, "smallest": 0, "largest": 0, "total_sum": 0}}
    
    estimated_time_left = 0
    start_time = time.perf_counter_ns()
    for i in range(times_to_run):
        clear()
        print(f"\nTempo para inicializar array com {len(arr):,d} ítens: {time_to_generate:,d} ns ({time_to_generate_millis:,.2f} ms)\n")
        print("Executando... " + str(i + 1) + "/" + str(times_to_run))
        print("Tempo restante estimado: " + str(round(estimated_time_left/1_000_000, 2)) + " ms")
        
        target = random.randint(0, len(arr) - 1)
        
        seq_result, seq_time = sequential_search(arr, target)
        bin_result, bin_time = binary_search(arr, target)
        
        search_times["sequential"].append(seq_time)
        search_times["sum"]["seq"] += seq_time
        
        search_times["binary"].append(bin_time)
        search_times["sum"]["bin"] += bin_time
        
        if target > search_times["largest_num"]["num"]:
            search_times["largest_num"]["num"] = target
            search_times["largest_num"]["seq_time"]["nano"] = seq_time
            search_times["largest_num"]["seq_time"]["millis"] = seq_time/1_000_000.0
            search_times["largest_num"]["bin_time"]["nano"] = bin_time
            search_times["largest_num"]["bin_time"]["millis"] = bin_time/1_000_000.0
            
        if target < search_times["smallest_num"]["num"]:
            search_times["smallest_num"]["num"] = target
            search_times["smallest_num"]["seq_time"]["nano"] = seq_time
            search_times["smallest_num"]["seq_time"]["millis"] = seq_time/1_000_000.0
            search_times["smallest_num"]["bin_time"]["nano"] = bin_time
            search_times["smallest_num"]["bin_time"]["millis"] = bin_time/1_000_000.0
            
        estimated_time_left = (times_to_run - i - 1) * (time.perf_counter_ns() - start_time) / (i + 1)
    
    search_times["averages"]["seq"]["nano"] = search_times["sum"]["seq"] / times_to_run
    search_times["averages"]["bin"]["nano"] = search_times["sum"]["bin"] / times_to_run
    search_times["averages"]["seq"]["millis"] = search_times["averages"]["seq"]["nano"] / 1_000_000.0
    search_times["averages"]["bin"]["millis"] = search_times["averages"]["bin"]["nano"] / 1_000_000.0
    
    search_times["differences"]["nano"] = (search_times["averages"]["seq"]["nano"] / search_times["averages"]["bin"]["nano"] - 1) * 100
    search_times["differences"]["millis"] = (search_times["averages"]["seq"]["millis"] / search_times["averages"]["bin"]["millis"] - 1) * 100
    search_times["differences"]["smallest"] = (search_times["smallest_num"]["seq_time"]["millis"] / search_times["smallest_num"]["bin_time"]["millis"] - 1) * 100
    search_times["differences"]["largest"] = (search_times["largest_num"]["seq_time"]["millis"] / search_times["largest_num"]["bin_time"]["millis"] - 1) * 100
    search_times["differences"]["total_sum"] = (search_times["sum"]["seq"] / search_times["sum"]["bin"] - 1) * 100
    
    print(f"\nFinalizado! Foram buscados {times_to_run:,d} ítens. Maior: {search_times["largest_num"]["num"]:,d} - Menor: {search_times["smallest_num"]["num"]:,d}\n\n")
   
    print(f"{'Tipo':>11} | {'Média (ns)':>10} | {'Média (ms)':>10} | {'Menor (ms)':>10} | {'Maior (ms)':>10} | {'Total (ms)':>8} |")
    print(f"{'Sequencial':>11} | {search_times['averages']['seq']['nano']:>10.0f} | {search_times['averages']['seq']['millis']:>10.4f} | {search_times['smallest_num']['seq_time']['millis']:>10.4f} | {search_times['largest_num']['seq_time']['millis']:>10.4f} | {search_times['sum']['seq']/1_000_000.0:>10.3f} |")
    print(f"{'Binária':>11} | {search_times['averages']['bin']['nano']:>10.0f} | {search_times['averages']['bin']['millis']:>10.4f} | {search_times['smallest_num']['bin_time']['millis']:>10.4f} | {search_times['largest_num']['bin_time']['millis']:>10.4f} | {search_times['sum']['bin']/1_000_000.0:>10.3f} |")
    print(f"{'':>11} | {'':>10} | {'':>10} | {'':>10} | {'':>10} | {'':>10} |")
    print(f"{'%Diferença':>11} | {search_times['differences']['nano']:>9.0f}% | {search_times['differences']['millis']:>9.0f}% | {search_times['differences']['smallest']:>9.0f}% | {search_times['differences']['largest']:>9.0f}% | {search_times['differences']['total_sum']:>9.0f}% |")
    print(f"{'*Diferença':>11} | {search_times['differences']['nano']//100:>9}x | {search_times['differences']['millis']//100:>9}x | {search_times['differences']['smallest']//100:>9}x | {search_times['differences']['largest']//100:>9}x | {search_times['differences']['total_sum']//100:>9}x |\n")
    print("A diferença denota quantas vezes o algoritmo binário é em relação ao sequencial, percentualmente e em vezes.\n")

main()
"""
        System.out.printf("\nFinalizado! Foram buscados %,d ítens. Maior: %,d - Menor: %,d\n\n", timesToRun, largestNum, smallestNum);

        System.out.printf("%11s | %10s | %10s | %10s | %10s | %8s |\n", "Tipo", "Média (ns)", "Média (ms)", "Menor (ms)", "Maior (ms)", "Total (ms)");
        System.out.printf("%11s | %10d | %10.4f | %10.4f | %10.4f | %10.3f |\n", "Sequencial", (long) averageSequentialSearchTime, averageSequentialSearchTimeMillis, seqSmallestMillis, seqLargestMillis, seqSearchSumMillis);
        System.out.printf("%11s | %10d | %10.4f | %10.4f | %10.4f | %10.3f |\n", "Binária", (long) averageBinarySearchTime, averageBinarySearchTimeMillis, binSmallestMillis, binLargestMillis, binSearchSumMillis);
        System.out.printf("%11s | %10s | %10s | %10s | %10s | %10s |\n", "", "", "", "", "", "");
        System.out.printf("%11s | %9d%% | %9d%% | %9d%% | %9d%% | %9d%% |\n", "%Diferença", differenceNano, differenceMillis, differenceSmallest, differenceLargest, differenceTotalSum);
        System.out.printf("%11s | %9dx | %9dx | %9dx | %9dx | %9dx |\n\n", "*Diferença", differenceNano/100, differenceMillis/100, differenceSmallest/100, differenceLargest/100, differenceTotalSum/100);
        System.out.println("A diferença denota quantas vezes o algoritmo binário é em relação ao sequencial, percentualmente e em vezes.\n");
    }
}
"""