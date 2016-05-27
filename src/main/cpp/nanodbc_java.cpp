#include "nanodbc_java.h"

namespace nanodbc
{

void execute(result& result, connection& conn, const string_type& query, long batch_operations, long timeout)
{
    result = execute(conn, query, batch_operations, timeout);
}

}
