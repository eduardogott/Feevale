# [HOBBY]

"""
Este arquivo foi usado para aproximar o tempo para calcular o fibonacci recursivamente, para valores acima de Fibonacci(60).

Não possui função sem ser a de aproximação.
"""

import numpy as np
import scipy.optimize as opt

results_rec = [(5, 0.0030), (10, 0.0046), (20, 0.2566), (30, 2.0507), (40, 250.4391), (45, 2714.5331), (50, 30371.7740), (55, 330328.6532)]

x_rec = np.array([x for x, _ in results_rec])
y_rec = np.array([y for _, y in results_rec])

x_iter = np.array([5, 10, 20, 30, 40, 45, 50, 55])
y_iter = np.array([...])

def exp_func(x, a, b, c):
    return a * np.exp(b * x + c)

params_rec, covariance_rec = opt.curve_fit(exp_func, x_rec, y_rec, maxfev=10000)

a, b, c = params_rec

print(f"\nEquação exponencial aproximada para fibonacci recursivo: y = {a:.6f} * exp({b:.6f} * x + {c:.6f})")

x_for_60_rec = exp_func(60, *params_rec)
x_for_70_rec = exp_func(70, *params_rec)
x_for_80_rec = exp_func(80, *params_rec)
x_for_90_rec = exp_func(90, *params_rec)
x_for_100_rec = exp_func(100, *params_rec)
x_for_110_rec = exp_func(110, *params_rec)
x_for_120_rec = exp_func(120, *params_rec)
x_for_130_rec = exp_func(130, *params_rec)

print(f"\nValor estimado para Fibonacci(60): {x_for_60_rec:.1f} ms \n({x_for_60_rec/60000:.1f} minutos / {x_for_60_rec/3600000:.2f} horas)\n")

print(f"Valor estimado para Fibonacci(70): {x_for_70_rec:.1f} ms \n({x_for_70_rec/3600000:.2f} horas / {x_for_70_rec/86400000:.2f} dias)\n")

print(f"Valor estimado para Fibonacci(80): {x_for_80_rec:.1f} ms \n({x_for_80_rec/86400000/30:.2f} meses / {x_for_80_rec/86400000/365:.2f} anos)\n")

print(f"Valor estimado para Fibonacci(90): {x_for_90_rec:.1f} ms \n({x_for_90_rec/86400000/30:.2f} meses / {x_for_90_rec/86400000/365:.2f} anos)\n")

print(f"Valor estimado para Fibonacci(100): {x_for_100_rec:.1f} ms \n({x_for_100_rec/86400000/365:.2f} anos)\n")

print(f"Valor estimado para Fibonacci(120): {x_for_120_rec:.1f} ms \n({x_for_120_rec/86400000/365:.2f} anos)\n")

print(f"Valor estimado para Fibonacci(130): {x_for_130_rec:.1f} ms \n({x_for_130_rec/86400000/365:.2f} anos / {x_for_130_rec/86400000/365/13_800_800_800:.2f} vezes a idade do universo)\n")
