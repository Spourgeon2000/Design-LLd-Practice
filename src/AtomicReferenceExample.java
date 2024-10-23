import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample {
    private AtomicReference<Person> personRef = new AtomicReference<>(new Person("John"));

    public void updatePerson(Person newPerson) {
        personRef.set(newPerson); // Atomically sets the new Person reference
        System.out.println(Thread.currentThread().getName() + " updated person to: " + personRef.get());
    }

    public void compareAndSetPerson(Person expectedPerson, Person newPerson) {
        if (personRef.compareAndSet(expectedPerson, newPerson)) {
            System.out.println(Thread.currentThread().getName() + " successfully updated person.");
        } else {
            System.out.println(Thread.currentThread().getName() + " failed to update person.");
        }
    }

    public Person getPerson() {
        return personRef.get(); // Retrieves the current person reference
    }

}