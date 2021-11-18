# coding=utf-8
import fnmatch
import locale
import os
import subprocess
import sys


# Procedimiento que ejecuta la aplicación con los argumentos indicados y retorna la salida
def ejecutar_proceso( arg1, arg2 ):
    # Crea el proceso
    p = subprocess.Popen( cmd.split(), stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, encoding=charset )

    # Lee los datos iniciales
    print(p.stdout.readline(), end='')
    print(p.stdout.readline(), end='')
    print(p.stdout.readline(), end='')
    print(p.stdout.readline(), end='')

    # Primer argumento
    print(p.stdout.readline(), end='')
    os.write(p.stdin.fileno(), (arg1 + "\n").encode())
    print(arg1)
    # Segundo argumento
    tmp = p.stdout.readline()
    print(tmp, end='')
    if not tmp.startswith("Ingrese"):
        return tmp
    else:
        print(arg2)
        os.write(p.stdin.fileno(), (arg2 + "\n").encode())

    # Almacena el resultado
    resultado = ""

    # Respuesta
    tmp = p.stdout.readline()

    # Flujo normal
    if tmp.strip() == "":
        print(tmp, end='')
        print(p.stdout.readline(), end='')
        print(p.stdout.readline(), end='')

        # Almacena el resultado
        resultado = resultado + p.stdout.readline()
        resultado = resultado + p.stdout.readline()
        resultado = resultado + p.stdout.readline()
        resultado = resultado + p.stdout.readline()

    # Excepción
    else:
        resultado = resultado + tmp

    return resultado

# Procedimiento que valida los resultados de las operaciones
def validar_resultados( row, texto_resultado ):
    # Plantilla del texto esperado
    texto_esperado = ""
    texto_esperado += "Suma de los argumentos:            (" + row["arg1"] + " + " + row["arg2"] + ") = _suma_\n"
    texto_esperado += "Resta de los argumentos:           (" + row["arg1"] + " - " + row["arg2"] + ") = _resta_\n"
    texto_esperado += "Producto de los argumentos:        (" + row["arg1"] + " x " + row["arg2"] + ") = _producto_\n"
    texto_esperado += "Cociente entero de los argumentos: (" + row["arg1"] + " / " + row["arg2"] + ") = _cociente_\n"

    # Reemplaza por los resultados
    texto_esperado = texto_esperado.replace("_suma_", row["suma"]).replace("_resta_", row["resta"]).replace("_producto_", row["producto"]).replace("_cociente_", row["cociente"])
    print("Resultado esperado")
    print(texto_esperado)

    # Compara
    if texto_esperado == texto_resultado:
        print("\tResultado de operaciones con " + row["arg1"] + " y " + row["arg2"] + " ---> OK")
    else:
        sys.exit("Error en prueba con " + row["arg1"] + " y " + row["arg2"])

# Procedimiento que valida los resultados de las excepciones
def validar_excepciones( row, texto_resultado ):
    # Error esperado
    print("Error esperado")
    print(row["error"])
    print()

    # Compara
    if row["error"] in texto_resultado:
        print("\tControl de excepciones con " + row["arg1"] + " y " + row["arg2"] + " ---> OK")
    else:
        sys.exit(row["mensaje"])



# Cambia la ruta de trabajo
os.chdir(os.path.dirname(os.path.realpath(__file__)))

# Obtiene el nombre del ejecutable
exe = fnmatch.filter(os.listdir('.'), '*.jar')[0]

# Comando para ejecutar la aplicación
cmd = "java -jar " + exe

# Obtiene el charset local
charset = locale.getdefaultlocale()[1]



# Datos de prueba para excepciones
data = [
    {"arg1": "1", "arg2": "0", "mensaje": "Validar división por cero", "error": "java.lang.ArithmeticException"}, 
    {"arg1": "n", "arg2": "0", "mensaje": "Validar ingreso de valor no numérico", "error": "Se esperaba un numero entero y se recibio n"}, 
    {"arg1": "0", "arg2": "t", "mensaje": "Validar ingreso de valor no numérico", "error": "Se esperaba un numero entero y se recibio t"}
]

# Validación de excepciones
for row in data:
    # Ejecuta el proceso
    salida = ejecutar_proceso( row["arg1"], row["arg2"] )

    # Resultado real
    print("Resultado real")
    print(salida)

    # Comprueba la salida
    validar_excepciones( row, salida )



# Datos de prueba para operaciones
data = [
    {"arg1": "81", "arg2": "9", "suma": "90", "resta": "72", "producto": "729", "cociente": "9"}, 
    {"arg1": "45", "arg2": "5", "suma": "50", "resta": "40", "producto": "225", "cociente": "9"}, 
    {"arg1": "6", "arg2": "3", "suma": "9", "resta": "3", "producto": "18", "cociente": "2"}, 
    {"arg1": "0", "arg2": "5", "suma": "5", "resta": "-5", "producto": "0", "cociente": "0"} 
]

# Verificación de operaciones
for row in data:
    # Ejecuta el proceso
    salida = ejecutar_proceso( row["arg1"], row["arg2"] )

    # Resultado real
    print("Resultado real")
    print(salida)

    # Comprueba la salida
    validar_resultados( row, salida )

