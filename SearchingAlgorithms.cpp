#include <iostream>
#include <cstdlib>
#include <stdexcept>
#include <thread>
#include <chrono>
#include <vector>
#include <string>
#include <random>
#include <iomanip>
#include <limits> 
#include <stdio.h>
#include <iomanip>

static void clear();
static long long timeFunction(const std::vector<long>& arr, long target, const std::string& function);
static void sequentialSearch(const std::vector<long>& arr, long target);
static void binarySearch(const std::vector<long>& arr, long target);

static void clear() {
    try {
        std::system("cls");
    } catch (const std::exception& e) {
        std::cerr << "Exception caught: " << e.what() << std::endl;
    }
}

static long long timeFunction(const std::vector<long>& arr, long target, const std::string& function) {
    auto startTime = std::chrono::high_resolution_clock::now();
    
    if (function == "sequential") {
        sequentialSearch(arr, target);
    } else if (function == "binary") {
        binarySearch(arr, target);
    }
    
    auto endTime = std::chrono::high_resolution_clock::now();
    std::chrono::duration<long long, std::nano> duration = endTime - startTime;
    return duration.count();
}

static void sequentialSearch(const std::vector<long>& arr, long target) {
    for (long num : arr) {
        if (num == target) {
            // Found the target
            return;
        }
    }
    return;
}

static void binarySearch(const std::vector<long>& arr, long target) {
    long left = 0;
    long right = arr.size() - 1;
    while (left <= right) {
        long mid = left + (right - left) / 2;
        if (arr[mid] == target) {
            // Found the target
            return;
        } else if (arr[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return;
}

int main() {
    const long timesToRun = 10000;
    const long arraySize = 2000000000;

    std::vector<long> arr(arraySize);
    auto startTime = std::chrono::high_resolution_clock::now();
    
    for (long i = 0; i < arraySize; ++i) {
        if (i % 10000000 == 0) {
            clear();
            std::cout << "Gerando array (vetor)... " << i << "/" << arraySize << std::endl;
        }
        arr[i] = i;
    }
    
    auto endTime = std::chrono::high_resolution_clock::now();
    long long timeToInitializeArray = std::chrono::duration_cast<std::chrono::nanoseconds>(endTime - startTime).count();
    double timeToInitializeArrayMillis = timeToInitializeArray / 1e6;

    std::vector<long long> sequentialSearchTimes;
    std::vector<long long> binarySearchTimes;

    long long largestNum = 0;
    long long smallestNum = std::numeric_limits<long long>::max();

    long long seqTimeForLargestNum = 0;
    long long seqTimeForSmallestNum = 0;

    long long binTimeForLargestNum = 0;
    long long binTimeForSmallestNum = 0;

    long long estimatedTimeLeft = 0;

    std::random_device rd;
    std::mt19937 gen(rd());
    std::uniform_int_distribution<long> dis(0, arraySize - 1);

    for (long i = 0; i < timesToRun; ++i) {
        clear();
        std::cout << "\nTempo para inicializar array com " << std::fixed << std::setw(10) << arraySize << " itens: "
                    << timeToInitializeArray << " ns (" << timeToInitializeArrayMillis << " ms)" << std::endl;
        std::cout << "Executando... " << (i + 1) << "/" << timesToRun << std::endl;
        std::cout << "Tempo restante estimado: " << estimatedTimeLeft / 1e6 << " ms" << std::endl;
        
        long target = dis(gen);
        
        long long sequential = timeFunction(arr, target, "sequential");
        long long binary = timeFunction(arr, target, "binary");

        sequentialSearchTimes.push_back(sequential);
        binarySearchTimes.push_back(binary);

        if (target > largestNum) {
            largestNum = target;
            seqTimeForLargestNum = sequential;
            binTimeForLargestNum = binary;
        }
        if (target < smallestNum) {
            smallestNum = target;
            seqTimeForSmallestNum = sequential;
            binTimeForSmallestNum = binary;
        }

        auto now = std::chrono::high_resolution_clock::now();
        long long elapsedTime = std::chrono::duration_cast<std::chrono::nanoseconds>(now - startTime).count();
        estimatedTimeLeft = (long long)((timesToRun - i - 1) * (static_cast<double>(elapsedTime) / (i + 1)));
    }

    double seqLargestMillis = seqTimeForLargestNum / 1e6;
    double seqSmallestMillis = seqTimeForSmallestNum / 1e6;
    double binLargestMillis = binTimeForLargestNum / 1e6;
    double binSmallestMillis = binTimeForSmallestNum / 1e6;

    long long sequentialSearchSum = 0;
    long long binarySearchSum = 0;

    for (long i = 0; i < timesToRun; ++i) {
        sequentialSearchSum += sequentialSearchTimes[i];
        binarySearchSum += binarySearchTimes[i];
    }

    double averageSequentialSearchTime = sequentialSearchSum / static_cast<double>(timesToRun);
    double averageBinarySearchTime = binarySearchSum / static_cast<double>(timesToRun);
    double averageSequentialSearchTimeMillis = averageSequentialSearchTime / 1e6;
    double averageBinarySearchTimeMillis = averageBinarySearchTime / 1e6;
    double seqSearchSumMillis = averageSequentialSearchTimeMillis * timesToRun;
    double binSearchSumMillis = averageBinarySearchTimeMillis * timesToRun;

    long long differenceNano = static_cast<long long>((averageSequentialSearchTime / averageBinarySearchTime - 1) * 100);
    long long differenceMillis = static_cast<long long>((averageSequentialSearchTimeMillis / averageBinarySearchTimeMillis - 1) * 100);
    long long differenceSmallest = static_cast<long long>((seqSmallestMillis / binSmallestMillis - 1) * 100);
    long long differenceLargest = static_cast<long long>((seqLargestMillis / binLargestMillis - 1) * 100);
    long long differenceTotalSum = static_cast<long long>((seqSearchSumMillis / binSearchSumMillis - 1) * 100);
    
    std::cout << "\nFinished! Searched " << timesToRun << " items. Largest: " << largestNum << " - Smallest: " << smallestNum << "\n\n";

    std::cout << std::setw(11) << "Tipo" << " | " 
                << std::setw(13) << "Media (ns)" << " | " 
                << std::setw(13) << "Media (ms)" << " | " 
                << std::setw(13) << "Menor (ms)" << " | " 
                << std::setw(13) << "Maior (ms)" << " | " 
                << std::setw(12) << "Total (ms)" << " |\n";
    std::cout << std::setw(11) << "Sequencial" << " | " 
                << std::fixed << std::setprecision(2)
                << std::setw(13) << averageSequentialSearchTime << " | " 
                << std::fixed << std::setprecision(5)
                << std::setw(13) << averageSequentialSearchTimeMillis << " | " 
                << std::setw(13) << seqSmallestMillis << " | " 
                << std::setw(13) << seqLargestMillis << " | " 
                << std::setw(12) << seqSearchSumMillis << " |\n";
    std::cout << std::setw(11) << "Binaria" << " | " 
                << std::fixed << std::setprecision(2)
                << std::setw(13) << averageBinarySearchTime << " | " 
                << std::fixed << std::setprecision(5)
                << std::setw(13) << averageBinarySearchTimeMillis << " | " 
                << std::setw(13) << binSmallestMillis << " | " 
                << std::setw(13) << binLargestMillis << " | " 
                << std::setw(12) << binSearchSumMillis << " |\n";
    std::cout << std::setw(11) << "" << " | " 
                << std::setw(13) << "" << " | " 
                << std::setw(13) << "" << " | " 
                << std::setw(13) << "" << " | " 
                << std::setw(13) << "" << " | " 
                << std::setw(12) << "" << " |\n";
    std::cout << std::setw(11) << "%Diferenca" << " | " 
                << std::setw(12) << differenceNano << "% | " 
                << std::setw(12) << differenceMillis << "% | " 
                << std::setw(12) << differenceSmallest << "% | " 
                << std::setw(12) << differenceLargest << "% | " 
                << std::setw(11) << differenceTotalSum << "% |\n";
    std::cout << std::setw(11) << "*Diferenca" << " | " 
                << std::setw(12) << differenceNano / 100 << "x | " 
                << std::setw(12) << differenceMillis / 100 << "x | " 
                << std::setw(12) << differenceSmallest / 100 << "x | " 
                << std::setw(12) << differenceLargest / 100 << "x | " 
                << std::setw(11) << differenceTotalSum / 100 << "x |\n\n";
    std::cout << "A diferenca denota quantas vezes o algoritmo binario e em relacao ao sequencial, percentualmente e em vezes.\n";

    return 0;
};