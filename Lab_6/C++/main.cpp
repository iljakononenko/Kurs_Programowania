#include <iostream>
#include <string>

using namespace std;

template <typename T>
class Tree_node
{
public:
    T data;
    Tree_node<T> *parent;
    Tree_node<T> *left_child;
    Tree_node<T> *right_child;

    Tree_node()
    {
    }

    Tree_node(T value, Tree_node<T> *parent)
    {
        this->data = value;
        this->parent = parent;
        this->left_child = NULL;
        this->right_child = NULL;
    }
};

template <typename T>
class BST
{
public:
    Tree_node<T> *root = NULL;

    void Insert(T value)
    {
        Tree_node<T> *y = NULL;
        Tree_node<T> *x = root;

        while (x != NULL)
        {
            y = x;
            if (value < x->data)
            {
                x = x->left_child;
            }
            else
            {
                x = x->right_child;
            }
        }

        Tree_node<T> *z = new Tree_node<T>(value, y);

        if (y == NULL)
        {
            root = z;
        }
        else if (value < y->data)
        {
            y->left_child = z;
        }
        else
        {
            y->right_child = z;
        }
    }

    Tree_node<T> *Search(Tree_node<T> *node, T value)
    {
        if (node == NULL || node->data == value)
        {
            return node;
        }

        if (value < node->data)
        {
            return Search(node->left_child, value);
        }
        else
        {
            return Search(node->right_child, value);
        }
    }

    Tree_node<T> *Tree_min(Tree_node<T> *node)
    {
        Tree_node<T> *min = node;
        while (min->left_child != NULL)
        {
            min = min->left_child;
        }
        return min;
    }

    Tree_node<T> *Successor(Tree_node<T> *node)
    {
        if (node->right_child != NULL)
        {
            return Tree_min(node->right_child);
        }

        Tree_node<T> *x = node;
        Tree_node<T> *y = node->parent;

        while (y != NULL && x == y->right_child)
        {
            x = y;
            y = y->parent;
        }
        return y;
    }

    void Transplant(Tree_node<T> *u, Tree_node<T> *v)
    {

        if (u->parent == NULL)
        {
            root = v;
        }
        else if (u == u->parent->left_child)
        {
            u->parent->left_child = v;
        }

        else
        {
            u->parent->right_child = v;
        }

        if (v != NULL)
        {
            v->parent = u->parent;
        }
    }

    void Delete(T data)
    {
        Tree_node<T> *element_to_delete = Search(root, data);

        if (element_to_delete != NULL)
        {
            if (element_to_delete->left_child == NULL)
            {
                Transplant(element_to_delete, element_to_delete->right_child);
            }
            else if (element_to_delete->right_child == NULL)
            {
                Transplant(element_to_delete, element_to_delete->left_child);
            }
            else
            {
                Tree_node<T> *y = Tree_min(element_to_delete->right_child);

                if (y->parent != element_to_delete)
                {
                    Transplant(y, y->parent);
                    y->right_child = element_to_delete->right_child;
                    y->right_child->parent = y;
                }
                Transplant(element_to_delete, y);
                y->left_child = element_to_delete->left_child;
                y->left_child->parent = y;
            }
        }
    }

    void Inorder(Tree_node<T> *node)
    {
        if (node != NULL)
        {
            Inorder(node->left_child);
            cout << "|" << node->data << "| ";
            Inorder(node->right_child);
        }
    }

    void Draw(Tree_node<T> *node)
    {
        if (node != NULL)
        {
            Draw(node->left_child);
            cout << "               |" << node->data << "| ";

            cout << endl;

            if (node->left_child != NULL)
            {
                cout << "left: |" << node->left_child->data << "|";
            }
            else
            {
                cout << "left: |NULL|";
            }

            if (node->right_child != NULL)
            {
                cout << "           right: |" << node->right_child->data << "| \n";
            }
            else
            {
                cout << "           right: |NULL| \n";
            }
            cout << "--------------------------------------\n";
            Draw(node->right_child);
        }
    }
};

int main()
{
    cout << endl
         << "Works!" << endl;

    BST<int> new_tree;
    new_tree.Insert(2);
    new_tree.Insert(5);
    new_tree.Insert(9);
    new_tree.Insert(1);
    new_tree.Insert(6);
    new_tree.Draw(new_tree.root);
    cout << "-----------------------------------------------------------------------------------------" << endl;
    Tree_node<int> *search_value = new_tree.Search(new_tree.root, 2);
    cout << endl
         << search_value->right_child->data << " " << search_value->left_child->data;

    cout << endl
         << "root data equals: " << new_tree.root->data << endl;

    new_tree.Delete(2);
    new_tree.Draw(new_tree.root);
    cout << "-----------------------------------------------------------------------------------------" << endl;
    cout << endl;
    new_tree.Insert(3);
    new_tree.Draw(new_tree.root);
    cout << "-----------------------------------------------------------------------------------------" << endl;

    return 0;
}
