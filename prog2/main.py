import re
import sys
import io

sys.stdin = io.TextIOWrapper(sys.stdin.buffer, encoding='utf-8')

def list_by_name_asc():
    sorted_names = pessoas
    sorted_names.sort(key=lambda x: (x['fname'], x['lname']))
    for pessoa in sorted_names:
        print(f"{pessoa['fname']} {pessoa['lname']}")

def list_by_name_dec():
    sorted_names = pessoas
    sorted_names.sort(key=lambda x: (x['fname'], x['lname']), reverse=True)
    for pessoa in sorted_names:
        print(f"{pessoa['fname']} {pessoa['lname']}")

def list_by_age_asc():
    sorted_ages = pessoas
    sorted_ages.sort(key=lambda x: (int(x['age'])))
    for pessoa in sorted_ages:
        print(f"{pessoa['fname']} {pessoa['lname']}")

def list_by_age_dec():
    sorted_ages = pessoas
    sorted_ages.sort(key=lambda x: (int(x['age'])), reverse=True)
    for pessoa in sorted_ages:
        print(f"{pessoa['fname']} {pessoa['lname']}")

def locate_name(nome_busca):
    def busca_binaria(nomes, nome_alvo):
        esquerda, direita = 0, len(nomes) - 1
        iteracoes = 0

        while esquerda <= direita:
            iteracoes += 1
            meio = (esquerda + direita) // 2
            if nomes[meio] == nome_alvo:
                return f"EXISTENTE {iteracoes}"
            elif nomes[meio] < nome_alvo:
                esquerda = meio + 1
            else:
                direita = meio - 1

        return f"INEXISTENTE {iteracoes}"

    lista_busca = pessoas
    lista_busca.sort(key=lambda x: (x['fname'], x['lname']))

    nomes = [f"{pessoa['fname']} {pessoa['lname']}" for pessoa in lista_busca]
    resultado = busca_binaria(nomes, nome_busca.strip())
    print(resultado)

def count_first_name(name):
    count = sum(1 for item in pessoas if item['fname'] == name)
    print(f"{count} ocorrências")

pessoas = []
def read_line(linha, proxima_linha=None):
    linha = linha.strip()

    try:
        groups = re.search(r"^(\w+)\s+(.+?)#(\d+)$", linha).groups()
        pessoas.append({"fname": groups[0], "lname": groups[1], "age": groups[2]})

    except AttributeError:
        if linha.isdigit():
            opt = int(linha)
            if opt == 1:
                list_by_name_asc()
            elif opt == 2:
                list_by_name_dec()
            elif opt == 3:
                list_by_age_asc()
            elif opt == 4:
                list_by_age_dec()
            elif opt == 5 and proxima_linha:
                locate_name(proxima_linha)
            elif opt == 6 and proxima_linha:
                count_first_name(proxima_linha)
            elif opt == 7:
                exit(0)

def main():
    linhas = sys.stdin.read().splitlines()
    i = 0
    while i < len(linhas):
        linha = linhas[i]
        proxima = linhas[i + 1] if (i + 1) < len(linhas) else None

        if linha.strip() in ['5', '6']:
            read_line(linha, proxima)
            i += 2
        else:
            read_line(linha)
            i += 1

main()