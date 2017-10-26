#ifndef RANDOM_HH
#define RANDOM_HH


double pickAnUniformDistributedNumber(double from, double to)
{
    // construct a trivial random generator engine from a time-based seed:
    unsigned seed = std::chrono::system_clock::now().time_since_epoch().count(); // :) abgefahren
    std::default_random_engine generator(seed);

    // generate number in range [from, to]
    std::uniform_real_distribution<double> distribution(from, to);

    return distribution(generator);
}

double pickAnExponentialDistributedNumber(double lambda)
{
    // construct a trivial random generator engine from a time-based seed:
    unsigned seed = std::chrono::system_clock::now().time_since_epoch().count(); // :) auch abgefahren
    std::default_random_engine generator(seed);

    // generate number, depended on lamda
    std::exponential_distribution<double> distribution(lambda);

    return distribution(generator);
}

#endif // RANDOM_HH

