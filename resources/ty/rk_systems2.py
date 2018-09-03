import math



def func(x, y, yp):
    ##yp[0] = y[0] + y[1] + x
   ## yp[1] = -4.0 * y[0] - 3.0 * y[1] + 2.0 * x
    yp[0] = -y[1]
    yp[1] = y[0]

def rk4(x1, x2, y, yp, h, neq):
    k0 = [0.0 for i in range(0, neq)]
    k1 = [0.0 for i in range(0, neq)]
    k2 = [0.0 for i in range(0, neq)]
    k3 = [0.0 for i in range(0, neq)]
    for i in range(0, neq):
        k0[i] = y[i]
    tk = x1
    func(tk, y, yp)
    for i in range(0, neq):
        k1[i] = h * yp[i]
        y[i] = k0[i] + (k1[i]) / 2.0
    tk = x1 + h / 2.0
    func(tk, y, yp)
    for i in range(0, neq):
        k2[i] = h * yp[i]
        y[i] = k0[i] + (k2[i]) / 2.0
    func(tk, y, yp)
    for i in range(0, neq):
        k3[i] = h * yp[i]
        y[i] = k0[i] + k3[i]
    tk = x2
    func(tk, y, yp)
    for i in range(0, neq):
        y[i] = k0[i] + k1[i] / 6.0 + k2[i] / 3.0 + k3[i] / 3.0 + (h * yp[i]) / 6.0
    # k1 = h*f(x, y)
    # k2 = h*f(x + (h / 2.0), y + (k1 / 2.0))
    # k3 = h*f(x + (h / 2.0), y + (k2 / 2.0))
    # k4 = h*f(x + h, y + k3)
    # y_next = (y + k1 / 6.0 + k2 / 3.0 + k3 / 3.0 + k4 / 6.0)


def find_accurate_y(t, neq):
    y = [0.0 for i in range(0, neq)]
   ## y[0] = (10.0 + 6.0 * t) * math.exp(-t) + 5.0 * t - 9.0
   ## y[1] = (-14.0 - 12.0 * t) * math.exp(-t) - 6.0 * t + 14.0
    y[0] = math.cos(t)
    y[1] = math.sin(t)
    return y


def func_main():
    a = 0.0
    b = 10.0
    delta = 0.01
    neq = 2
    nt = int((b - a) / delta)
    h = (b - a)/float(nt)
    array_of_y = [1.0, 0.0]
    array_of_yp = [0.0, 0.0]
    for it in range(0, nt):
        x1 = a + float(it) * h
        x2 = a + float(it + 1) * h
        rk4(x1, x2, array_of_y, array_of_yp, h, neq)
        accurate_array = find_accurate_y(x2, neq)
        print(x2, [math.fabs(accurate_array[i] - array_of_y[i]) for i in range(0, neq)])


if __name__ == '__main__':
    func_main()

