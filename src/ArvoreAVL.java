public class ArvoreAVL {
       // Implementação básica de uma árvore AVL
    static class AVLTree {
        private Node root;

        private class Node {
            int key;
            Node left, right;
            int height;

            Node(int key) {
                this.key = key;
                this.height = 1;
            }
        }

        // Função para calcular a altura de um nó
        private int height(Node node) {
            return (node != null) ? node.height : 0;
        }

        // Função para obter o fator de balanceamento de um nó
        private int getBalance(Node node) {
            return (node != null) ? height(node.left) - height(node.right) : 0;
        }

        // Função para rotacionar à direita em torno de um nó
        private Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;

            // Realizar a rotação
            x.right = y;
            y.left = T2;

            // Atualizar alturas
            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;

            // Retornar nova raiz
            return x;
        }

        // Função para rotacionar à esquerda em torno de um nó
        private Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;

            // Realizar a rotação
            y.left = x;
            x.right = T2;

            // Atualizar alturas
            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;

            // Retornar nova raiz
            return y;
        }

        // Função para inserir um nó na árvore AVL
        public void insert(int key) {
            root = insert(root, key);
        }

        private Node insert(Node node, int key) {
            // Passo 1: Realizar a inserção normal de BST
            if (node == null) {
                return new Node(key);
            }

            if (key < node.key) {
                node.left = insert(node.left, key);
            } else if (key > node.key) {
                node.right = insert(node.right, key);
            } else {
                // Chaves iguais não são permitidas em árvores BST
                return node;
            }

            // Passo 2: Atualizar a altura do nó atual
            node.height = 1 + Math.max(height(node.left), height(node.right));

            // Passo 3: Obter o fator de balanceamento deste nó para verificar se ele se tornou desequilibrado
            int balance = getBalance(node);

            // Se o nó se tornar desequilibrado, existem quatro casos

            // Caso da rotação à direita
            if (balance > 1 && key < node.left.key) {
                return rightRotate(node);
            }

            // Caso da rotação à esquerda
            if (balance < -1 && key > node.right.key) {
                return leftRotate(node);
            }

            // Caso da rotação à esquerda-direita
            if (balance > 1 && key > node.left.key) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // Caso da rotação à direita-esquerda
            if (balance < -1 && key < node.right.key) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            // Nó não desequilibrado, retornar o próprio nó
            return node;
        }

        // Função para imprimir a árvore AVL em ordem
        public void print() {
            print(root);
        }

        private void print(Node node) {
            if (node != null) {
                print(node.left);
                System.out.print(node.key + " ");
                print(node.right);
            }
        }

        // Dentro da classe AVLTree em ArvoreAVL.java

// Função para remover um nó da árvore AVL
public void remove(int key) {
    root = remove(root, key);
}

private Node remove(Node node, int key) {
    if (node == null) {
        return null;
    }

    if (key < node.key) {
        node.left = remove(node.left, key);
    } else if (key > node.key) {
        node.right = remove(node.right, key);
    } else {
        // Nó encontrado, realizar a remoção
        if (node.left == null || node.right == null) {
            node = (node.left != null) ? node.left : node.right;
        } else {
            Node successor = findMin(node.right);
            node.key = successor.key;
            node.right = remove(node.right, successor.key);
        }
    }

    if (node != null) {
        // Atualizar a altura do nó atual
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Verificar o balanceamento do nó
        int balance = getBalance(node);

        // Casos de desbalanceamento

        // Rotação à direita
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }

        // Rotação à esquerda
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }

        // Rotação à esquerda-direita
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Rotação à direita-esquerda
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
    }

    return node;
}


    // Função auxiliar para encontrar o nó mínimo em uma subárvore
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // Função para contar quantas vezes um número aparece na árvore AVL
    public int countOccurrences(int key) {
        return countOccurrences(root, key);
    }

    private int countOccurrences(Node node, int key) {
        if (node == null) {
            return 0;
        }

        int count = (node.key == key) ? 1 : 0;
        count += countOccurrences(node.left, key);
        count += countOccurrences(node.right, key);

        return count;
    }

    public int contar(int chave) {
        return contar(root, chave);
    }
    
    private int contar(Node node, int chave) {
        if (node == null) {
            return 0;
        }
    
        int count = 0;
    
        if (node.key == chave) {
            count++;
        }
    
        count += contar(node.left, chave);
        count += contar(node.right, chave);
    
        return count;
    }
}
}