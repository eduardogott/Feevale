import json

with open('processos.json', 'r') as f:
    processos_json = json.load(f)

# Loop através de cada processo e cria o arquivo
for processo in processos_json:
    # Define o nome do arquivo com base no PID
    filename = f"processo_{processo['pid']}.txt"
    
    # Conteúdo do arquivo
    conteudo = (
        f"TEMPO_CHEGADA={processo['tempo_chegada']}\n"
        f"BURST={processo['burst']}\n"
        f"PRIORIDADE={processo['prioridade']}"
    )
    
    # Cria e escreve o arquivo
    with open(filename, "w") as file:
        file.write(conteudo)

print("Arquivos de processos gerados com sucesso!")