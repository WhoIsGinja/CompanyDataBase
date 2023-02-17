package woo.app.products;

import pt.tecnico.po.ui.Command;                                                                                                              import pt.tecnico.po.ui.DialogException;                                                                                                      import pt.tecnico.po.ui.Input;                                                                                                                import woo.Storefront;                                                                                                                        //FIXME import other classes

import woo.app.exceptions.DuplicateProductKeyException;
import woo.app.exceptions.UnknownSupplierKeyException;
import woo.app.exceptions.UnknownServiceTypeException;
import woo.app.exceptions.UnknownServiceLevelException;

import woo.exceptions.DuplicateKeyException;
import woo.exceptions.SupplierKeyDoesntExistException;
import woo.exceptions.ServiceTypeDoesntExistException;
import woo.exceptions.ServiceLevelDoesntExistException;

/**
 * Register container.
 */
public class DoRegisterProductContainer extends Command<Storefront> {

  Input<String> productId;
  Input<Integer> price;
  Input<Integer> criticalValue;
  Input<String> supplierId;
  Input<String> serviceType;
  Input<String> serviceLevel;

  public DoRegisterProductContainer(Storefront receiver) {
    super(Label.REGISTER_CONTAINER, receiver);
    
    productId = _form.addStringInput(Message.requestProductKey());
    price = _form.addIntegerInput(Message.requestPrice());
    criticalValue = _form.addIntegerInput(Message.requestStockCriticalValue());
    supplierId = _form.addStringInput(Message.requestSupplierKey());
    serviceType = _form.addStringInput(Message.requestServiceType());
    serviceLevel = _form.addStringInput(Message.requestServiceLevel());
  }

  @Override
  public final void execute() throws DialogException {
    _form.parse();

    try {
      _receiver.registerProductContainer(productId.value(), serviceType.value(), serviceLevel.value(), supplierId.value(), price.value(), criticalValue.value());
    
    } catch (DuplicateKeyException e) {
      throw new DuplicateProductKeyException(productId.value());
      
    } catch (SupplierKeyDoesntExistException e) {
      throw new UnknownSupplierKeyException(supplierId.value());

    } catch (ServiceTypeDoesntExistException e) {
      throw new UnknownServiceTypeException(serviceType.value());

    } catch(ServiceLevelDoesntExistException e) {
      throw new UnknownServiceLevelException(serviceLevel.value());
    }
  }
}
