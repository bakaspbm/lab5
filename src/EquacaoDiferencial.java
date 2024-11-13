public class EquacaoDiferencial {

    // Função f(x, y) da equação diferencial y' + xy = 0.5 * (x - 1) * e^x * y^2
    public static double func(double x, double y) {
        return 0.5 * (x - 1) * Math.exp(x) * Math.pow(y, 2) - x * y;
    }

    // Método de Euler
    public static double euler(double x0, double y0, double h, double xFinal) {
        double y = y0;
        for (double x = x0; x < xFinal; x += h) {
            y += h * func(x, y);
        }
        return y;
    }

    // Método de Runge-Kutta de 4ª ordem (RK4)
    public static double rungeKutta(double x0, double y0, double h, double xFinal) {
        double y = y0;
        for (double x = x0; x < xFinal; x += h) {
            double k1 = h * func(x, y);
            double k2 = h * func(x + h / 2, y + k1 / 2);
            double k3 = h * func(x + h / 2, y + k2 / 2);
            double k4 = h * func(x + h, y + k3);
            y += (k1 + 2 * k2 + 2 * k3 + k4) / 6;
        }
        return y;
    }

    // Solução exata (aproximada ou analítica)
    public static double solucaoExata(double x) {
        // Aproximação ou solução exata da função (se conhecida)
        // Aqui, apenas colocamos um placeholder pois a solução exata não foi fornecida.
        // return algum_valor_calculado; // Substitua conforme a solução exata disponível
        return 0; // Placeholder - insira a função exata, se disponível
    }

    // Função para calcular o erro máximo entre as soluções aproximada e exata
    public static double erroMaximo(double x0, double y0, double h, double xFinal) {
        double maxErro = 0;
        double yEuler, yRungeKutta, yExata;

        for (double x = x0; x <= xFinal; x += h) {
            yEuler = euler(x0, y0, h, x);
            yRungeKutta = rungeKutta(x0, y0, h, x);
            yExata = solucaoExata(x);
            maxErro = Math.max(maxErro, Math.abs(yExata - yRungeKutta)); // ou comparar yEuler, se necessário
        }
        return maxErro;
    }

    public static void main(String[] args) {
        double x0 = 0; // Ponto inicial x
        double y0 = 2; // Condição inicial y(0) = 2
        double xFinal = 2; // Limite final do intervalo
        double h = 0.1; // Passo de integração (ajuste para precisão desejada)

        // Solução numérica usando o método de Euler
        double solucaoEuler = euler(x0, y0, h, xFinal);
        System.out.printf("Solução com Método de Euler em x = %.2f: y ≈ %.6f\n", xFinal, solucaoEuler);

        // Solução numérica usando o método de Runge-Kutta de 4ª ordem
        double solucaoRungeKutta = rungeKutta(x0, y0, h, xFinal);
        System.out.printf("Solução com Método de Runge-Kutta em x = %.2f: y ≈ %.6f\n", xFinal, solucaoRungeKutta);

        // Solução exata (ou aproximada) no ponto xFinal
        double solucaoExata = solucaoExata(xFinal);
        System.out.printf("Solução Exata em x = %.2f: y ≈ %.6f\n", xFinal, solucaoExata);

        // Erro máximo entre a solução exata e as soluções numéricas
        double erroMaximoRungeKutta = erroMaximo(x0, y0, h, xFinal);
        System.out.printf("Erro máximo (Runge-Kutta vs Solução Exata): %.6f\n", erroMaximoRungeKutta);
    }
}
