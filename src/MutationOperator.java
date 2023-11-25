public interface MutationOperator {

    public boolean permutationIsPreserved();
    public void mutate(Solution solution);
}
